package app.game;

import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.level.BossLevel;
import app.ui.ConfirmBox;
import app.ui.CustomizedButton;
import app.ui.MainMenu;
import app.ui.ViewManager;
import app.util.AppLogger;
import app.util.DataManager;
import app.util.Window;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class models the game itself: in here, the current level is loaded.
 * The behavioural pattern Input-Update-Render is managed in this class.
 */
public class Game extends Application {

    /**
     * The number of levels in this game.
     */
    public static final int LEVEL_COUNT = 2;
    private static final String GAME_OVER_SPRITE = "gameover.png";
    private static final String YOU_WIN_SPRITE = "youWin.png";
    private static final double FRAME_RATE = 60;
    private static final double FRAME_DURATION = 1000 / FRAME_RATE;
    private static final int MIN_WINDOW_HEIGHT = 600;
    private static final int MIN_WINDOW_WIDTH = 800;
    private static final int PROGRESS_BAR_HEIGHT = 50;
    private static final int PROGRESS_BAR_WIDTH = 300;
    private static final int PROGRESS_BAR_LAYOUTX = 30;
    private static final int PROGRESS_BAR_LAYOUTY = 20;
    private static final int LABEL_LAYOUTY = 5;
    private static final int OFFSET = 10;
    private static final int BOSS_OFFSET = 20;
    private static final int FONT_SIZE = 50;
    private Level currentLevel;
    private Group root;
    private Scene currentScene;
    private InputManager inputManager;
    private Image image;
    private final ProgressBar playerHealthBar = new ProgressBar(0);
    private final ProgressBar bossHealthBar = new ProgressBar(0);
    private final Label timeLabel = new Label();
    private final Label coinsLabel = new Label();
    private final AnchorPane anchorPane = new AnchorPane();
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private final BooleanProperty victory = new SimpleBooleanProperty(false);
    private Timeline tl;
    private long startTime;
    private int coinsCollected;
    private int enemiesDefeated;

    /**
     * Creates a new instance of the class Game
     * by loading the level from the given json file.
     *
     * @param filename the file of the level
     * @throws IOException when loading errors occurs
     */
    public Game(final String filename) throws IOException {
        this.currentLevel = new DataManager().loadLevel(filename);
    }

    /**
     * Creates a new instance of the class Game,
     * by loading the level from the json file.
     *
     * @throws IOException the exception thrown by the
     * constructor if problems while reading the file are detected.
     */
    public Game() throws IOException {
        this.currentLevel = new DataManager().loadLevel("level1.json");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) {
        stage.setTitle("UNIBOssfight");

        stage.setOnCloseRequest(e -> {
            e.consume();
            if (ConfirmBox.display("\tAre you sure you\nwant to quit the game?")) {
                stage.close();
            }
        });

        initHUD();

        final InputStream input = getClass().getClassLoader()
                .getResourceAsStream("assets/ground/ground.png");
        if (input != null) {
            this.image = new Image(input);
        } else {
            AppLogger.getLogger().warning("Error occurred while loading the ground");
        }

        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinHeight(MIN_WINDOW_HEIGHT);
        stage.setMinWidth(MIN_WINDOW_WIDTH);

        this.root = new Group();

        this.anchorPane.getChildren().add(root);

        //Creating a scene object
        this.currentScene = new Scene(anchorPane);

        this.inputManager = new InputManager(currentScene);

        this.currentScene.heightProperty().addListener(
                (observable, oldValue, newValue) ->
                        Window.setHeight(currentScene.getHeight()
                                - this.image.getHeight())
        );

        this.currentScene.widthProperty().addListener(
                (observable, oldValue, newValue) ->
                        Window.setWidth(currentScene.getWidth())
        );

        this.gameOver.addListener(
                (observable, oldValue, newValue) -> {
                    final CustomizedButton restartButton = new CustomizedButton(
                            "RESTART");
                    if (this.currentLevel.getLevelNumber() == 1) {
                        new SecondaryStage(stage, restartButton,
                                "level1.json", GAME_OVER_SPRITE).show();
                    } else {
                        new SecondaryStage(stage, restartButton,
                                "level2.json", GAME_OVER_SPRITE).show();
                    }
                }
        );

        this.victory.addListener(
                (observable, oldValue, newValue) -> {
                    if (this.currentLevel.getLevelNumber() == 1) {
                        final CustomizedButton nextLevelButton = new CustomizedButton(
                                "LEVEL 2");
                        new SecondaryStage(stage, nextLevelButton,
                                "level2.json", YOU_WIN_SPRITE).show();
                    } else {
                        final CustomizedButton restartButton = new CustomizedButton(
                                "RESTART");
                        new SecondaryStage(stage, restartButton,
                                "level1.json", YOU_WIN_SPRITE).show();
                    }
                }
        );

        //Adding scene to the stage
        stage.setScene(currentScene);

        //Displaying the contents of the stage
        stage.show();

        this.tl = new Timeline(new KeyFrame(Duration.millis(FRAME_DURATION),
            e -> {
                if (!this.currentLevel.isOver()) {
                    if (this.currentLevel.isLevelEnded()
                            && !(this.currentLevel instanceof BossLevel)) {
                        try {
                            this.coinsCollected = this.currentLevel
                                    .getPlayer()
                                    .getCoinsCollected();
                            this.enemiesDefeated = this.currentLevel
                                    .getDefeatedEnemiesCount();
                            loadBossLevel();
                        } catch (final IOException ex) {
                            AppLogger.getLogger()
                                .severe("Errore nel caricamento del boss level "
                                + ex.getMessage());
                        }
                    } else if (this.currentLevel.isLevelEnded()) {
                        this.victory.set(true);
                        saveScore();
                        this.tl.stop();
                    }
                    run();
                } else {
                    this.gameOver.set(true);
                    this.tl.stop();
                }
            })
        );
        tl.setCycleCount(Animation.INDEFINITE);

        tl.play();
        this.currentLevel.init();
        this.startTime = System.currentTimeMillis();
    }

    private void loadBossLevel() throws IOException {
        this.currentLevel = new DataManager()
                .loadBossLevel("bossLevel" + this.currentLevel.getLevelNumber() + ".json");
        this.currentLevel.init();
        initHUD();
    }

    private void saveScore() {
        try {
            final Score scores = new DataManager().deserializeScore("score.json");
            scores.setLevelStats(this.currentLevel.getLevelNumber(),
                    this.enemiesDefeated,
                    this.coinsCollected);
            new DataManager().serializeScore(scores);
        } catch (final IOException e) {
            AppLogger.getLogger().warning("An error occurred while saving the score");
        }
    }

    private void initHUD() {
        this.playerHealthBar.setPrefSize(PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        this.playerHealthBar.setLayoutY(PROGRESS_BAR_LAYOUTY);

        this.bossHealthBar.setPrefSize(PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        this.bossHealthBar.setStyle("-fx-accent: red;");
        this.bossHealthBar.setLayoutY(PROGRESS_BAR_LAYOUTY);

        this.timeLabel.setLayoutY(LABEL_LAYOUTY);
        this.coinsLabel.setLayoutY(LABEL_LAYOUTY);
        this.coinsLabel.setTextFill(Color.GOLD);

        ViewManager.setFont("HUDfont.ttf", FONT_SIZE,
                List.of(this.coinsLabel, this.timeLabel));
    }

    private void update() {
        this.currentLevel.updateEntities();
        if (this.inputManager.isSpacePressed) {
            this.currentLevel.updatePlayer(Entity.Inputs.SPACE);
        }
        if (this.inputManager.isDPressed) {
            this.currentLevel.updatePlayer(Entity.Inputs.RIGHT);
        }
        if (this.inputManager.isAPressed) {
            this.currentLevel.updatePlayer(Entity.Inputs.LEFT);
        }
        this.currentLevel.updatePlayer(Entity.Inputs.EMPTY);

        this.currentLevel.collision();
    }

    private void render() {
        this.root.getChildren().clear();

        renderHUD();

        root.getChildren().addAll(this.currentLevel.renderBackground());

        this.root.getChildren().addAll(this.currentLevel.renderUniqueEntities());
        this.currentLevel.renderEntities().forEach(e -> this.root.getChildren().add(e));

        // create a Rectangle
        final Rectangle rect = new Rectangle(0, Window.getHeight(),
                Window.getWidth(), this.image.getHeight());

        // set fill for rectangle
        final ImagePattern imagePattern = new ImagePattern(
                this.image,
                -this.currentLevel.getPlayerPosition().getX(),
                Window.getHeight() - this.image.getHeight(),
                this.image.getWidth(), this.image.getHeight(),
                false
        );

        rect.setFill(imagePattern);

        this.root.getChildren().addAll(rect, this.playerHealthBar,
                this.timeLabel, this.coinsLabel);

        if (this.currentLevel instanceof BossLevel) {
            this.root.getChildren().add(this.bossHealthBar);
        }
    }

    private void renderHUD() {
        final long elapsed = System.currentTimeMillis() - this.startTime;
        final String timeStamp = String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(elapsed),
                TimeUnit.MILLISECONDS.toSeconds(elapsed)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsed)));

        this.timeLabel.setText(timeStamp);
        this.coinsLabel.setText(Integer.toString(this.currentLevel
                    .getPlayer().getCoinsCollected()));

        this.playerHealthBar.setProgress(
                (double) this.currentLevel.getPlayer().getHealth().getValue()
                        / this.currentLevel.getPlayer().getHealth().getMaxValue()
        );

        this.playerHealthBar.setLayoutX(PROGRESS_BAR_LAYOUTX);
        this.timeLabel.setLayoutX(Window.getWidth()
                - (this.timeLabel.getWidth() + OFFSET));
        this.coinsLabel.setLayoutX(Window.getWidth() / 2
                    - this.coinsLabel.getWidth() / 2);

        renderBossHUD();
    }

    private void renderBossHUD() {
        if (this.currentLevel instanceof final BossLevel bl) {
            this.bossHealthBar.setProgress((double) bl.getBoss()
                    .getHealth().getValue()
                    / bl.getBoss().getHealth().getMaxValue());
            this.bossHealthBar.setLayoutX(Window.getWidth()
                    - (this.bossHealthBar.getWidth() + BOSS_OFFSET));

            this.timeLabel.setLayoutX(Window.getWidth() / 2
                    - this.timeLabel.getWidth() / 2);

            this.timeLabel.setTextFill(Color.WHITE);
            this.coinsLabel.setTextFill(Color.TRANSPARENT);
        }
    }

    private void run() {
        update();
        render();
    }

    private class InputManager {
        private final Scene scene;
        private boolean isAPressed;
        private boolean isDPressed;
        private boolean isSpacePressed;

        InputManager(final Scene scene) {
            this.scene = scene;

            this.scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case A -> {
                        this.isAPressed = true;
                    }
                    case D -> {
                        this.isDPressed = true;
                    }
                    case SPACE -> {
                        this.isSpacePressed = true;
                    }
                    default -> {
                        AppLogger.getLogger().warning("Unexpected Input from keyboard.");
                    }
                }
            });

            this.scene.setOnMouseClicked(e -> currentLevel.playerShoot(
                            new Point2D(e.getX() + currentLevel
                                    .getPlayerPosition()
                                    .getX() - Window.getWidth() / 2,
                                    Window.getHeight() - e.getY())
                    )
            );

            this.scene.setOnMouseMoved(e -> currentLevel.rotatePlayerWeapon(new Point2D(
                    e.getX() + currentLevel.getPlayerPosition().getX() - Window.getWidth() / 2,
                    Window.getHeight() - e.getY())));

            this.scene.setOnKeyReleased(e -> {
                switch (e.getCode()) {
                    case A -> {
                        this.isAPressed = false;
                    }
                    case D -> {
                        this.isDPressed = false;
                    }
                    case SPACE -> {
                        this.isSpacePressed = false;
                    }
                    default -> {
                        AppLogger.getLogger().warning("Unexpected Input release from keyboard.");
                    }
                }
            });
        }

    }

    private static class SecondaryStage extends Stage {
        private static final int IMG_LAYOUTX = 150;
        private static final int IMG_LAYOUTY = 15;
        private static final int BUTTON_LAYOUTX = 155;
        private static final int HOME_BUTTON_LAYOUTY = 140;
        private static final int RESTART_BUTTON_LAYOUTY = 210;
        private static final int SCENE_WIDTH = 500;
        private static final int SCENE_HEIGHT = 300;


        SecondaryStage(final Stage gameStage, final CustomizedButton button,
                       final String jsonFile, final String imgUrl) {
            super();
            this.initModality(Modality.APPLICATION_MODAL);
            this.setOnCloseRequest(event -> gameStage.close());

            final AnchorPane pane = new AnchorPane();
            pane.prefWidth(SCENE_HEIGHT);

            final CustomizedButton homeButton = new CustomizedButton("HOME");

            homeButton.setOnAction(event -> {
                this.close();
                gameStage.close();
                Platform.runLater(() -> {
                    new MainMenu().start(new Stage());
                });
            });

            button.setOnAction(event -> Platform.runLater(() -> {
                this.close();
                gameStage.close();
                try {
                    new Game(jsonFile).start(new Stage());
                } catch (final IOException e) {
                    AppLogger.getLogger().severe(e.getMessage());
                    throw (IllegalStateException) new IllegalStateException().initCause(e);
                }
            }));

            ViewManager.addImage(IMG_LAYOUTX, IMG_LAYOUTY, imgUrl, pane);
            ViewManager.setBackground("blue.png", SCENE_WIDTH, SCENE_HEIGHT, pane);

            homeButton.setLayoutX(BUTTON_LAYOUTX);
            homeButton.setLayoutY(HOME_BUTTON_LAYOUTY);
            button.setLayoutX(BUTTON_LAYOUTX);
            button.setLayoutY(RESTART_BUTTON_LAYOUTY);

            pane.getChildren().addAll(homeButton, button);
            setScene(new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT));
        }
    }
}
