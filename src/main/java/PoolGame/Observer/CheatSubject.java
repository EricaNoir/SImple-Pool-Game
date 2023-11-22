package PoolGame.Observer;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

/** The subject for cheating function which will control BallObserver, ScoreObserver and StateKeeperObserver */
public class CheatSubject implements Subject{

    private List<CheatObserver> list = new ArrayList<CheatObserver>();

    /** Add new observer */
    public void attach(CheatObserver cheatObserver) {
        list.add(cheatObserver);
    }

    /** Remove an observer */
    public void detach(CheatObserver cheatObserver) {
        list.remove(cheatObserver);
    }

    /**
     * Notify all observers
     * @param keyCode The keyCode of the key pressed
     */
    public void notifyObservers(KeyCode keyCode) {
        for (CheatObserver ce : list) {
            ce.cheat(keyCode);
        }
    }
}
