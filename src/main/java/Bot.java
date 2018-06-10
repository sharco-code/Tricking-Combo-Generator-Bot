import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {

    private String text;
    private Long chatid;
    private Integer replyid;

    private Boolean addtrickname = false;
    private Boolean adddifficulty = false;
    private Boolean addlanding = false;

    String newTrick = null;
    int newDifficulty = 0;
    int newLanding = 0;

    public SendMessage sendMessage(String text, Long chatid, Integer replyid) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatid);
        sendMessage.setReplyToMessageId(replyid);
        return sendMessage;
    }

    public void onUpdateReceived(Update update) {

        text = null;
        chatid = null;
        replyid = null;

        if(addtrickname == true) {
            newTrick = update.getMessage().getText();
            addtrickname = false;
            text = "dificult:";
            chatid = update.getMessage().getChatId();
            try {
                execute(sendMessage(text, chatid, replyid));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            adddifficulty = true;
        } else if (adddifficulty == true) {
            newDifficulty = Integer.parseInt(update.getMessage().getText());
            adddifficulty = false;
            text = "landing:";
            chatid = update.getMessage().getChatId();
            try {
                execute(sendMessage(text, chatid, replyid));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            addlanding = true;
        } else if (addlanding == true) {
            newLanding = Integer.parseInt(update.getMessage().getText());
            addlanding = false;
            text = "trick saved";
            chatid = update.getMessage().getChatId();
            try {
                execute(sendMessage(text, chatid, replyid));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if("/newtrick".equals(update.getMessage().getText())) {
            text = "Write a trick name, example: aerial";
            chatid = update.getMessage().getChatId();
            addtrickname = true;
            try {
                execute(sendMessage(text, chatid, replyid));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(newTrick != null && newDifficulty != 0 && newLanding != 0) {
            ArrayList array = new ArrayList();
            Tricks trick = new Tricks();
            trick.setTrick(newTrick, newDifficulty, newLanding);
            System.out.println(trick.getTrickName()  + "\n" + trick.getTrickDifficulty() + "\n" + trick.getTrickLanding());
        }

    }

    public String getBotUsername() {
        return "Tricking Combo Generator";
    }

    public String getBotToken() {
        return "API_TOKEN";
    }
}
