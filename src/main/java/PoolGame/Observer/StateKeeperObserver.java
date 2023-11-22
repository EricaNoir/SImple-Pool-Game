package PoolGame.Observer;

import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Singleton.ScoreBoard;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.HashMap;

import static PoolGame.App.originator;
import static PoolGame.App.stateKeeper;


/**
 * The stateKeeper observer for cheating function which will save current state after receiving notification
 */
public class StateKeeperObserver implements CheatObserver{

    public HashMap<KeyCode, Color> keyMap = new HashMap<KeyCode, Color>();
    public Game game;

    public StateKeeperObserver(Game game) {
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
     * Save state
     * @param keyCode The keyCode of the key pressed
     */
    @Override
    public void cheat(KeyCode keyCode) {
        Color color = this.keyMap.get(keyCode);
        for (Ball ball : this.game.getPoolTable().getBalls()) {
            if (ball.getColour() == color && !ball.isDisabled()) {
                stateKeeper.setMemento(originator.SaveState(false));
                break;
            }
        }
    }
}
