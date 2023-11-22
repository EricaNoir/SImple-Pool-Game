package PoolGame.Singleton;

import javafx.scene.text.Text;

import java.util.Timer;

/** The Timer using Singleton design pattern */
public class GameTimer {
    private static GameTimer gameTimer = new GameTimer();
    private static Timer timer = new Timer();
    private static Task timerTask = null;

    /** Private constructor */
    private GameTimer() {
    }

    /** Start the timer from 0:00 */
    public void startTimer() {
        timerTask = new Task();
        timer.schedule(timerTask, 0, 1000);
    }

    /** End the timer */
    public void endTimer() {
        timer.cancel();

        timer = new Timer();
    }

    /** Reset the timer to 0:00 */
    public void resetTimer() {
        timerTask.reset();
    }

    /**
     * Set the timer starting time to timeInt
     * @param timeInt The starting time
     */
    public static void setTime(int timeInt) {
        timerTask.setTimeInt(timeInt);
    }

    /**
     * Get the time elapsed
     * @return Time elapsed
     */
    public static int getTime() {
        return timerTask.getTimeInt();
    }


    /** Get the timer instance
     * @return The timer instance
     */
    public static GameTimer getGameTimer() {
        return gameTimer;
    }


    /**
     * Get the timer text to put into the stage
     * @return The timer Text
     */
    public static Text getTimeText() {
        Text timeText = timerTask.getTimeText();
        return timeText;
    }

}
