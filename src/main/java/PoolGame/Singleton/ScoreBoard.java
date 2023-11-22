package PoolGame.Singleton;


import PoolGame.Items.Ball;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashMap;

import static PoolGame.Items.Ball.BallType.CUEBALL;

/** The Score board using Singleton design pattern */
public class ScoreBoard {
    private static ScoreBoard scoreBoard = new ScoreBoard();
    private static int score = 0;
    private static Text scoreText = new Text("Score: " + score);
    private final HashMap<Color, Integer> scoreMap = new HashMap<>() {
    };


    /** Private constructor */
    private ScoreBoard() {
        scoreMap.put(Color.RED, 1);
        scoreMap.put(Color.YELLOW, 2);
        scoreMap.put(Color.GREEN, 3);
        scoreMap.put(Color.BROWN, 4);
        scoreMap.put(Color.BLUE, 5);
        scoreMap.put(Color.PURPLE, 6);
        scoreMap.put(Color.BLACK, 7);
        scoreMap.put(Color.ORANGE, 8);
    }

    /**
     * Get the ScoreBoard instance
     * @return The scoreBoard instance
     */
    public static ScoreBoard getScoreBoard() {
        return scoreBoard;
    }


    /**
     * When a ball falls into the pocket, add corresponding scores to the score board.
     * @param ball The ball falls into pocket.
     */
    public void gainScore(Ball ball) {
        if (ball.getBallType() != CUEBALL) {
            score += scoreMap.get(ball.getColour());
            scoreText.setText("Score: " + score);
        }
    }

    /**
     * Get the score text to put into the stage
     * @return The score Text
     */
    public static Text getScoreText() {
        return scoreText;
    }

    /** Reset the ScoreBoard to 0:00 */
    public void resetScore() {
        score = 0;
        scoreText.setText("Score: " + score);
    }

    public static void setScore(int scoreInt) {
        score = scoreInt;
        scoreText.setText("Score: " + score);
    }

    /**
     * Get the score
     * @return The score
     */
    public static int getScore() { return score; }

}

