package PoolGame;

import java.util.ArrayList;
import java.util.List;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Config.BallConfig;
import PoolGame.Config.PocketConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import PoolGame.Singleton.GameTimer;
import PoolGame.Singleton.ScoreBoard;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

/** The game class that runs the game */
public class Game {
    private PoolTable table;
    public boolean shownWonText = false;
    private final Text winText = new Text(50, 50, "Win and Bye");

    private GameTimer gameTimer = GameTimer.getGameTimer();
    private ScoreBoard scoreBoard = ScoreBoard.getScoreBoard();

    /**
     * Initialise the game with the provided config
     * @param config The config parser to load the config from
     */
    public Game(ConfigReader config) {
        this.setup(config);
    }

    private void setup(ConfigReader config) {
        this.table = new PoolTable(config.getConfig().getTableConfig());
        //add balls
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<Ball> balls = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();
        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }
        this.table.setupBalls(balls);

        //add pockets
        List<PocketConfig> pocketsConf = config.getConfig().getTableConfig().getPocketsConfig().getPocketConfigs();
        List<Pocket> pockets = new ArrayList<>();
        for (PocketConfig pocketConf: pocketsConf) {
            Pocket pocket = new Pocket(pocketConf.getPositionConfig().getX(), pocketConf.getPositionConfig().getY(), pocketConf.getRadius());
            if (pocket == null) {
                System.err.println("WARNING: Unknown pocket, skipping...");
            }
            else {
                pockets.add(pocket);
            }
        }
        this.table.setupPockets(pockets);

        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 2 - 140);
        this.winText.setY(table.getDimY() / 2 + 10);
    }

    public void setWonTextVisible(boolean wonTextVisible) {
        this.shownWonText = wonTextVisible;
        this.winText.setVisible(wonTextVisible);
    }

    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /**
     * Set the table of the current game.
     * @param table The table to set to the game
     */
    public void setTable(PoolTable table) {
        this.table = table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        this.winText.setFont(Font.font(50));
        this.winText.setFill(Color.WHITE);
        groupChildren.add(this.winText);

        // add borders to hide the pockets
        this.addBorder(root);

        // add instruction text
        this.addInstruction(root);

        // add timer
        this.addTimer(root);

        // add score
        this.addScore(root);
    }

    /** Code to add score text. */
    private void addScore(Group root) {
        scoreBoard.resetScore();
        Text scoreText = scoreBoard.getScoreText();

        scoreText.setX(400);
        scoreText.setY(this.getPoolTable().getDimY()+55);
        scoreText.setFill(Color.BLUE);
        scoreText.setFont(Font.font(30));

        root.getChildren().add(scoreText);
    }

    /** Code to add two white border to hide the pockets. */
    private void addBorder(Group root) {
        // add white rect
        Rectangle r = new Rectangle();
        r.setX(0);
        r.setY(this.table.getDimY()); // under the table
        r.setWidth(this.table.getDimX() + 30); // magic number
        r.setHeight(212); // magic number
        r.setFill(Color.WHITE);
        Rectangle r2 = new Rectangle();
        r2.setX(this.table.getDimX());
        r2.setY(0);
        r2.setWidth(30); // magic number
        r2.setHeight(this.table.getDimY() + 30); // magic number
        r2.setFill(Color.WHITE);
        root.getChildren().add(r);
        root.getChildren().add(r2);
    }

    public void setGameScore(int gameScore) {
        ScoreBoard.setScore(gameScore);
    }

    /** Code to add timer text. */
    private void addTimer(Group root) {
        gameTimer.startTimer();

        Text timeText = gameTimer.getTimeText();

        timeText.setX(30);
        timeText.setY(this.getPoolTable().getDimY()+55);
        timeText.setFill(Color.BLUE);
        timeText.setFont(Font.font(30));

        root.getChildren().add(timeText);
    }

    /**
     * Set the starting time of the timer
     * @param timeInt The starting time
     */
    public void setGameTime(int timeInt) {
        GameTimer.setTime(timeInt);
    }

    /** Code to add instruction text. */
    //EricaChange add instruction
    private void addInstruction(Group root) {

        Text text = new Text(20, 95 + this.table.getDimY(), "You can control the game by pressing different keys on your keyboard. \n" +
                                                                        "- Changing game mode: \n" +
                                                                        "  * EASY: '1', NORMAL: '2', HARD: '3'\n" +
                                                                        "- Undo last operation: Press 'Z' to undo your last operation (including hit cue ball, change mode and cheat). \n" +
                                                                        "- Cheat: Press different keys to make one colour of balls disappear and gain corresponding score. \n" +
                                                                        "  * Red: 'R', Yellow: 'Y', Green: 'G', Brown: 'W' \n" +
                                                                        "    Blue: 'B', Purple: 'P', Black: 'K', Orange: 'O'");

        root.getChildren().add(text);
    }

    /** Reset the game */
    public void reset() {
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();
        gameTimer.resetTimer();
        scoreBoard.resetScore();

    }

    /** Code to execute every tick. */
    public void tick() {
        if (table.hasWon() && !this.shownWonText) {
            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
            gameTimer.endTimer();
        }
        table.checkPocket(this);
        table.handleCollision();
        this.table.applyFrictionToBalls();
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }
    }
}
