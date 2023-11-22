package PoolGame.Memento;

import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Items.Pocket;
import PoolGame.Singleton.GameTimer;
import PoolGame.Singleton.ScoreBoard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Store internal states of the originator
 */
public class Memento {
    // ball information
    private ArrayList<double[]> ballPositionList;
    private ArrayList<double[]> ballVelocityList;
    private ArrayList<Boolean> ballDisableList;
    private ArrayList<Integer> ballFallCounterList;
    private ArrayList<Boolean> ballVisibleList;

    // game information
    private boolean showWonText;
    private int gameTime;
    private int gameScore;

    // table information
    private long[] dim;
    private String colourName;
    private Color colour;
    private double friction;
    private Rectangle shape;
    private List<Ball> balls;
    private List<Pocket> pockets;

    public boolean isTableChanged() {
        return tableChanged;
    }

    // self information
    private boolean tableChanged;

    /**
     * Create a memento with a giving game.
     * Store the balls information, table information and game information as state.
     * @param game The game object with internal state supposed to be saved
     * @param tableChanged Is game mode changed or not
     */
    public Memento(Game game, boolean tableChanged) {
        if (!tableChanged) {
            this.tableChanged = false;
            this.setBallPositionList(game);
            this.setBallVelocityList(game);
            this.setBallDisableList(game);
            this.setBallFallCounterList(game);
            this.setBallVisibleList(game);
        }
        else {
            this.tableChanged = true;
            this.setDim(game);
            this.setColourName(game);
            this.setColour(game);
            this.setFriction(game);
            this.setShape(game);
            this.setBalls(game);
            this.setPockets(game);
        }

        this.setShowWonText(game);
        this.setGameTime(game);
        this.setGameScore(game);
    }

    /**
     * Get the balls position
     * @return List of balls positions
     */
    public ArrayList<double[]> getBallPositionList() {
        return ballPositionList;
    }


    public void setBallPositionList(Game game) {
        ballPositionList = new ArrayList<double[]>();
        for (Ball balls : game.getPoolTable().getBalls()) {
            double[] tempPos = {balls.getXPos(), balls.getYPos()};
            ballPositionList.add(tempPos);
        }
    }

    /**
     * Get the balls velocity
     * @return List of balls velocity
     */
    public ArrayList<double[]> getBallVelocityList() {
        return ballVelocityList;
    }


    public void setBallVelocityList(Game game) {
        ballVelocityList = new ArrayList<double[]>();
        for (Ball balls : game.getPoolTable().getBalls()) {
            double[] tempVel = {balls.getXVel(), balls.getYVel()};
            ballVelocityList.add(tempVel);
        }
    }

    /**
     * Get the balls disabled or not
     * @return List of balls disabled or not
     */
    public ArrayList<Boolean> getBallDisableList() {
        return ballDisableList;
    }


    public void setBallDisableList(Game game) {
        ballDisableList = new ArrayList<Boolean>();
        for (Ball balls : game.getPoolTable().getBalls()) {
            ballDisableList.add(balls.isDisabled());
        }
    }

    /**
     * Get the balls fall count
     * @return List of balls fall count
     */
    public ArrayList<Integer> getBallFallCounterList() {
        return ballFallCounterList;
    }


    public void setBallFallCounterList(Game game) {
        ballFallCounterList = new ArrayList<Integer>();
        for (Ball balls : game.getPoolTable().getBalls()) {
            ballFallCounterList.add(balls.getFallCounter());
        }
    }

    /**
     * Get the balls visibility
     * @return List of balls visibility
     */
    public ArrayList<Boolean> getBallVisibleList() {
        return ballVisibleList;
    }

    public void setBallVisibleList(Game game) {
        ballVisibleList = new ArrayList<Boolean>();
        for (Ball balls : game.getPoolTable().getBalls()) {
            ballVisibleList.add(!balls.isDisabled());
        }
    }

    /**
     * Get the game isShowWonText state
     * @return Game isShowWonText state
     */
    public boolean isShowWonText() {
        return showWonText;
    }

    public void setShowWonText(Game game) {
        showWonText = game.shownWonText;
    }

    /**
     * Get the time elapsed
     * @return Time elapsed in second
     */
    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(Game game) {
        gameTime = GameTimer.getTime();
    }

    /**
     * Get the game score gained
     * @return Game score gained
     */
    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(Game game) {
        gameScore = ScoreBoard.getScore();
    }

    /**
     * Get the table dimension
     * @return Table dimension as {width, height}
     */
    public long[] getDim() {
        return dim;
    }

    public void setDim(Game game) {
        this.dim = new long[]{game.getPoolTable().getDimX(), game.getPoolTable().getDimY()};
    }

    /**
     * Get the table colour name
     * @return Table colour name
     */
    public String getColourName() {
        return colourName;
    }

    public void setColourName(Game game) {
        this.colourName = game.getPoolTable().getColourName();
    }

    /**
     * Get the table colour
     * @return Table colour
     */
    public Color getColour() {
        return colour;
    }

    public void setColour(Game game) {
        this.colour = game.getPoolTable().getColour();
    }

    /**
     * Get the table friction
     * @return Table friction
     */
    public double getFriction() {
        return friction;
    }

    public void setFriction(Game game) {
        this.friction = game.getPoolTable().getFriction();
    }

    /**
     * Get the table shape
     * @return Table shape
     */
    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Game game) {
        this.shape = new Rectangle(this.dim[0], this.dim[1], this.colour);
    }

    /**
     * Get the balls
     * @return The list of balls
     */
    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(Game game) {
        this.balls = new ArrayList<Ball>();
        for (Ball b : game.getPoolTable().getBalls()) {
            balls.add(b);
        }
    }

    /**
     * Get the pockets
     * @return The list of pockets
     */
    public List<Pocket> getPockets() {
        return pockets;
    }

    public void setPockets(Game game) {
        this.pockets = new ArrayList<Pocket>();
        for (Pocket p : game.getPoolTable().getPockets()) {
            pockets.add(p);
        }
    }
}
