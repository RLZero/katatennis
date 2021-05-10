package tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    private Player player1;
    private Player player2;
    private Game game;
    private static final int THREE_ROUND = 3;
    private static final int FOUR_ROUND = 4;
    private static final String SPACE = " ";

    @BeforeEach
    public void init() {
        player1 = new Player("Robert", new Score(0, ScoreDescription.LOVE.toString()));
        player2 = new Player("Enemy", new Score(0, ScoreDescription.LOVE.toString()));
        game = new Game(player1, player2);
    }

    @Test
    public void should_increase_point_when_player_won_point() {
        game.wonPointBy(player1);

        assertEquals(1, player1.getScore().getPoint());
        assertEquals(ScoreDescription.FIFTEEN.toString(), player1.getScore().getDescription());
    }

    @Test
    public void should_return_game_score() {
        game.wonPointBy(player1);
        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.FIFTEEN + "-" + ScoreDescription.LOVE, score);
    }

    @Test
    public void should_return_deuce_when_score_is_forty_forty() {
        setupDeuceScenario();

        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.DEUCE.toString(), score);
    }

    @Test
    public void should_return_advantage_player1() {
        setupDeuceScenario();
        game.wonPointBy(player1);

        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.ADVANTAGE.toString(), score);
        assertTrue(player1.isAdvantage());
    }

    @Test
    public void should_return_advantage_player2() {
        setupDeuceScenario();
        game.wonPointBy(player2);

        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.ADVANTAGE.toString(), score);
        assertTrue(player2.isAdvantage());
    }

    @Test
    public void should_return_game_set_when_player1_won_by_at_least_two_more_points() {
        for (int i = 0; i < FOUR_ROUND; i++) {
            game.wonPointBy(player1);
        }
        game.wonPointBy(player2);
        game.wonPointBy(player2);

        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.GAME_SET + SPACE + player1.getName(), score);
    }

    @Test
    public void should_return_game_set_when_player_won_two_more_points_at_deuce() {
        setupDeuceScenario();
        game.wonPointBy(player1);
        game.wonPointBy(player1);

        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.GAME_SET + SPACE + player1.getName(), score);
    }

    @Test
    public void should_reset_score_to_deuce() {
        for (int i = 0; i < FOUR_ROUND; i++) {
            game.wonPointBy(player1);
            game.wonPointBy(player2);
        }

        String score = game.getScore(player1, player2);

        assertEquals(ScoreDescription.DEUCE.toString(), score);
        assertEquals(ScorePoint.THREE, player1.getScore().getPoint());
        assertEquals(ScorePoint.THREE, player2.getScore().getPoint());
    }

    private void setupDeuceScenario() {
        for (int i = 0; i < THREE_ROUND; i++) {
            game.wonPointBy(player1);
            game.wonPointBy(player2);
        }
    }
}
