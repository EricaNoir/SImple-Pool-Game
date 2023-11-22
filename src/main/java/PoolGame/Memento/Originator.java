package PoolGame.Memento;

import PoolGame.App;
import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Items.Pocket;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Originator to create mementos or restore the game with mementos from caretaker
 */
public class Originator {

    private ArrayList<double[]> ballPositionList;
    private ArrayList<double[]> ballVelocityList;
    private ArrayList<Boolean> ballDisableList;
    private ArrayList<Integer> ballFallCounterList;
    private ArrayList<Boolean> ballVisibleList;

    private boolean showWonText;
    private int gameTime;
    private int gameScore;

    private long[] dim;
    private String colourName;
    private Color colour;
    private double friction;
    private Rectangle shape;
    private java.util.List<Ball> balls;
    private List<Pocket> pockets;

    private Game currentGame;

    /**
     * Save current game as a memento
     * @param tableChanged Is game mode changed or not
     * @return Memento with internal states from current game
     */
    public Memento SaveState(boolean tableChanged) {
        return (new Memento(currentGame, tableChanged));
    }

    /**
     * Save the initial state at app start
     * @param game Default game
     */
    public void stateInit(Game game) {
        this.currentGame = game;
    }

    /**
     * Recover the game with a given memento
     * @param memento The memento which supposed to be recovered
     */
    public void RecoverState(Memento memento) {
        if (!memento.isTableChanged()) {
            this.ballPositionList = memento.getBallPositionList();
            this.ballVelocityList = memento.getBallVelocityList();
            this.ballDisableList = memento.getBallDisableList();
            this.ballFallCounterList = memento.getBallFallCounterList();
            this.ballVisibleList = memento.getBallVisibleList();
            this.showWonText = memento.isShowWonText();
            this.gameTime = memento.getGameTime();
            this.gameScore = memento.getGameScore();
            int count = 0;
            for (Ball balls : currentGame.getPoolTable().getBalls()) {
                balls.setXPos(memento.getBallPositionList().get(count)[0]);
                balls.setYPos(memento.getBallPositionList().get(count)[1]);

                balls.setXVel(memento.getBallVelocityList().get(count)[0]);
                balls.setYVel(memento.getBallVelocityList().get(count)[1]);

                balls.setDisabled(memento.getBallDisableList().get(count));
                balls.setFallCounter(memento.getBallFallCounterList().get(count));
                balls.setVisible(memento.getBallVisibleList().get(count));

                count += 1;
            }

        }

        else {
            this.dim = memento.getDim();
            this.colourName = memento.getColourName();
            this.colour = memento.getColour();
            this.friction = memento.getFriction();
            this.shape = memento.getShape();
            this.balls = memento.getBalls();
            this.pockets = memento.getPockets();

            currentGame.getPoolTable().setDim(memento.getDim());
            currentGame.getPoolTable().setColourName(memento.getColourName());
            currentGame.getPoolTable().setColour(memento.getColour());
            currentGame.getPoolTable().setFriction(memento.getFriction());
            currentGame.getPoolTable().setShape(memento.getShape());
            currentGame.getPoolTable().setBalls(memento.getBalls());
            currentGame.getPoolTable().setPockets(memento.getPockets());
        }

        currentGame.setWonTextVisible(memento.isShowWonText());
        currentGame.setGameTime(memento.getGameTime());
        currentGame.setGameScore(memento.getGameScore());
    }
}
