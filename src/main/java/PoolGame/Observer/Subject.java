package PoolGame.Observer;

import javafx.scene.input.KeyCode;

import java.util.List;

/**
 * Subject interface
 */
public interface Subject {

    /** Add new observer */
    public void attach(CheatObserver cheatObserver);


    /** Remove an observer */
    public void detach(CheatObserver cheatObserver);

    /**
     * Notify all observers
     * @param keyCode The key pressed
     */
    public void notifyObservers(KeyCode keyCode);
}
