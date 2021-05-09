package tennis;

public class Game {

    private Player player1;
    private Player player2;
    private Player winner;
    private static final String SPACE = " ";

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String wonPointBy(Player player) {
        player.getScore().addPoint();
        return player.getName();
    }

    private boolean isDeuce() {
        return player1.getScore().getPoint() == ScorePoint.THREE && player2.getScore().getPoint() == ScorePoint.THREE;
    }

    private void resetAdvantagePointAtDeuce() {
        player1.getScore().minusPoint();
        player2.getScore().minusPoint();
        player1.setAdvantage(false);
        player2.setAdvantage(false);
    }

    private boolean isAdvantage() {
        boolean advantagePlayer1 = player1.getScore().getPoint() == ScorePoint.FOUR
                && player2.getScore().getPoint() == ScorePoint.THREE;
        boolean advantagePlayer2 = player1.getScore().getPoint() == ScorePoint.THREE
                && player2.getScore().getPoint() == ScorePoint.FOUR;

        if (advantagePlayer1) {
            player1.setAdvantage(true);
            return true;
        } else if (advantagePlayer2) {
            player2.setAdvantage(true);
            return true;
        }
        return false;
    }

    private boolean gameSet() {
        if (player1.getScore().getPoint() > ScorePoint.THREE &&
                (player1.getScore().getPoint() - player2.getScore().getPoint() >= ScorePoint.TWO)) {
            winner = player1;
            return true;
        } else if(player2.getScore().getPoint() > ScorePoint.THREE
                && (player2.getScore().getPoint() - player1.getScore().getPoint() >= ScorePoint.TWO)) {
            winner = player2;
            return true;
        }
        return false;
    }

    public String getScore(Player player1, Player player2) {
        if (player1.getScore().getPoint() == ScorePoint.FOUR && player2.getScore().getPoint() == ScorePoint.FOUR) {
            resetAdvantagePointAtDeuce();
        }
        if (isDeuce()) {
            return ScoreDescription.DEUCE.toString();
        } else if (isAdvantage()) {
            return ScoreDescription.ADVANTAGE.toString();
        } else if (gameSet()) {
            return ScoreDescription.GAME_SET + SPACE + winner.getName();
        }
        return player1.getScore().getDescription() + "-" + player2.getScore().getDescription();
    }
}
