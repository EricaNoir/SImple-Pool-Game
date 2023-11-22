package PoolGame.Observer;

import javafx.scene.input.KeyCode;


/** Observer Interface */
public interface CheatObserver {

    /**
     * Disable corresponding balls and gain scores
     * @param keyCode The keyCode of the key pressed
     */
    public void cheat(KeyCode keyCode);
}
