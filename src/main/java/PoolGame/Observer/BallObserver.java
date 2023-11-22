package PoolGame.Observer;

import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Memento.Memento;
import PoolGame.Singleton.ScoreBoard;
import javafx.scene.input.KeyCode;

import java.awt.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static PoolGame.App.originator;
import static PoolGame.App.stateKeeper;

/**
 * The ball observer for cheating function which will disable balls after receiving notification
 */
public class BallObserver implements CheatObserver{
    public HashMap<KeyCode, Color> keyMap = new HashMap<KeyCode, Color>();
    public Game game;

    public BallObserver(Game game) {
        keyMap.put(KeyCode.R, Color.RED);
        keyMap.put(KeyCode.Y, Color.YELLOW);
        keyMap.put(KeyCode.G, Color.GREEN);
        keyMap.put(KeyCode.W, Color.BROWN);
        keyMap.put(KeyCode.B, Color.BLUE);
        keyMap.put(KeyCode.P, Color.PURPLE);
        keyMap.put(KeyCode.K, Color.BLACK);
        keyMap.put(KeyCode.O, Color.ORANGE);
        this.game = game;
    }

    /**
     * Disable corresponding balls
     * @param keyCode The keyCode of the key pressed
     */
    @Override
    public void cheat(KeyCode keyCode) {

        Color color = this.keyMap.get(keyCode);

        for (Ball ball : this.game.getPoolTable().getBalls()) {
            if (ball.getColour() == color && !ball.isDisabled()) {
                ball.disable();
                ScoreBoard.getScoreBoard().gainScore(ball);
            }
        }

    }
}
