package PoolGame.Singleton;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.TimerTask;


/** Extends TimerTask class to allow the timer return Text object and reset. */
public class Task extends TimerTask {

    private int timeInt;
    private Text timeText;

    public Task() {
        this.timeInt = 0;
        this.timeText = new Text(Integer.toString(timeInt));
    }

    public Text getTimeText() {
        return this.timeText;
    }

    public void reset() {
        this.timeInt = 0;
    }

    public void setTimeInt(int timeInt) {
        this.timeInt = timeInt;
    }
    public int getTimeInt() {
        return this.timeInt;
    }

    @Override
    public void run() {
        String timeStr = "Time elapsed -  " + timeInt/60 + " : " + timeInt%60;
        this.timeText.setText(timeStr);
        this.timeInt ++;
    }
}
