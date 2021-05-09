package tennis;

public class Player {

    private String name;
    private Score score;
    private boolean isAdvantage;

    public Player(String name, Score score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public boolean isAdvantage() {
        return isAdvantage;
    }

    public void setAdvantage(boolean advantage) {
        isAdvantage = advantage;
    }
}
