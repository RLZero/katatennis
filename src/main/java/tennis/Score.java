package tennis;

public class Score {

    private int point;
    private String description;

    public Score(int point, String description) {
        this.point = point;
        this.description = description;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint() {
        this.point += 1;
        initDescription();
    }

    public void minusPoint() {
        this.point -= 1;
        initDescription();
    }

    public String getDescription() {
        return description;
    }

    public void initDescription() {
        switch (point) {
            case 0 :
                this.description  = ScoreDescription.LOVE.toString();
                break;
            case 1 :
                this.description = ScoreDescription.FIFTEEN.toString();
                break;
            case 2 :
                this.description = ScoreDescription.THIRTY.toString();
                break;
            case 3 :
                this.description = ScoreDescription.FORTY.toString();
                break;
        }
    }
}
