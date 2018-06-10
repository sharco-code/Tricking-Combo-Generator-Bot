public class Tricks {

    public String trick;
    public int difficulty; // 1 Very Easy - 2 Easy - 3 Normal - 4 Hard - 5 Ultra Hard - 6 WTF
    public int landing; // 1 Front - 2 Back - 3 Side

    public Tricks() {
        trick = "";
        difficulty = 0;
        landing = 0;
    }
    public void setTrick(String trick, int difficulty, int landing) {
        this.trick = trick;
        this.difficulty = difficulty;
        this.landing = landing;
    }
    public String getTrickName() {
        return this.trick;
    }
    public int getTrickDifficulty() {
        return this.difficulty;
    }
    public int getTrickLanding() {
        return this.landing;
    }

}