package app.game;

import app.core.entity.Entity;
import app.core.level.Level;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class models the game itself: in here, the current level is loaded.
 * The behavioural pattern Input-Update-Render is managed in this class.
 */
public class Game extends Application {

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
    private static final int FONT_SIZE = 50;
    private final Level currentLevel;
    private Stage mainStage;
    private Group root;
    private Scene currentScene;
    private InputManager inputManager;
    private Image image;
    private Image bg;
    private final ProgressBar progressBar = new ProgressBar(0);
    private final Label timeLabel = new Label();
    private final Label coinsLabel = new Label();
    private final AnchorPane anchorPane = new AnchorPane();
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private long startTime;

    /**
     * Creates a new instance of the class Game,
     * by loading the level from the json file.
     *
     * @throws IOException the exception thrown by the
     * constructor if problems while reading the file are detected.
     */
    public Game() throws IOException {
        this.currentLevel = new DataManager().loadLevel("output.json");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) {

        this.mainStage = stage;

        this.mainStage.setTitle("UNIBOssfight");

        this.mainStage.setOnCloseRequest(e -> {
            e.consume();
            saveState();
        });

        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();

        initHUD();

        FileInputStream input = null;
        try {
            input = new FileInputStream("assets/ground/ground.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.image = new Image(input);

        try {
            this.bg = new Image(new FileInputStream("assets/background.png"),
                    Window.getWidth(), Window.getHeight(), false, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.mainStage.setX(bounds.getMinX());
        this.mainStage.setY(bounds.getMinY());
        this.mainStage.setWidth(bounds.getWidth());
        this.mainStage.setHeight(bounds.getHeight());
        this.mainStage.setMinHeight(MIN_WINDOW_HEIGHT);
        this.mainStage.setMinWidth(MIN_WINDOW_WIDTH);

        this.root = new Group();

        this.anchorPane.getChildren().add(root);

        //Creating a scene object
        currentScene = new Scene(anchorPane);

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
                (observable, oldValue, newValue) ->
                        new GameOverStage(this.mainStage).show()
        );

        this.currentScene.setOnMouseClicked(e -> this.currentLevel.playerShoot(
                new Point2D(e.getX() + this.currentLevel
                        .getPlayerPosition()
                        .getX() - Window.getWidth() / 2,
                        Window.getHeight() - e.getY())
                )
        );

        //Adding scene to the stage
        this.mainStage.setScene(currentScene);

        //Displaying the contents of the stage
        this.mainStage.show();

        final Timeline tl = new Timeline(new KeyFrame(Duration.millis(FRAME_DURATION),
            e -> {
                if (!this.currentLevel.isOver()) {
                    run();
                } else {
                    gameOver.set(true);
                }
            })
        );
        tl.setCycleCount(Animation.INDEFINITE);

        tl.play();
        this.currentLevel.init();
        this.startTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception {
        try {
            new DataManager().serializeLevel(this.currentLevel);
        } catch (final Exception e) {
            AppLogger.getLogger().severe(e.getMessage());
        }
        super.stop();
    }

    private void initHUD() {
        this.progressBar.setPrefSize(PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        this.progressBar.setStyle("#00bbf0");

        this.progressBar.setLayoutY(PROGRESS_BAR_LAYOUTY);
        this.timeLabel.setLayoutY(LABEL_LAYOUTY);
        this.coinsLabel.setLayoutY(LABEL_LAYOUTY);

        ViewManager.setFont("src/main/resources/HUDfont.ttf", FONT_SIZE,
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
        ImagePattern imagePattern = new ImagePattern(
                this.image,
                -this.currentLevel.getPlayerPosition().getX(),
                Window.getHeight() - this.image.getHeight(),
                this.image.getWidth(), this.image.getHeight(),
                false
        );

        rect.setFill(imagePattern);

        this.root.getChildren().addAll(rect, this.progressBar,
                this.timeLabel, this.coinsLabel);
    }

    private void renderHUD() {
        final long elapsed = System.currentTimeMillis() - this.startTime;
        final String timeStamp = String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(elapsed),
                TimeUnit.MILLISECONDS.toSeconds(elapsed)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsed)));

        this.progressBar.setProgress(this.currentLevel.getPlayer().getHealth().getValue() / 100.0);
        this.progressBar.setLayoutX(PROGRESS_BAR_LAYOUTX);

        this.timeLabel.setText(timeStamp);
        this.timeLabel.setLayoutX(Window.getWidth() - (this.timeLabel.getWidth() + OFFSET));

        this.coinsLabel.setText(Integer.toString(this.currentLevel
                .getPlayer().getCoinsCollected()));
        this.coinsLabel.setLayoutX((Window.getWidth() / 2)
                - this.coinsLabel.getWidth() / 2);
    }

    private void run() {
        update();
        render();
    }

    private class InputManager {
        private final Scene scene;
        private boolean isAPressed = false;
        private boolean isDPressed = false;

        private boolean isSpacePressed = false;

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

    private void saveState() {
        boolean answer = ConfirmBox.display("Do you want to save the state?");
        if (answer) {
            try {
                this.stop();
                System.exit(0);
            } catch (Exception e) {
                AppLogger.getLogger().severe(e.getMessage());
            }
        }
    }

    private static class GameOverStage extends Stage {
        private static final int SCENE_WIDTH = 500;
        private static final int SCENE_HEIGHT = 300;
        private static final int LOGO_LAYOUTX = 150;
        private static final int LOGO_LAYOUTY = 15;
        private static final int BUTTON_LAYOUTX = 155;
        private static final int HOME_BUTTON_LAYOUTY = 140;
        private static final int RESTART_BUTTON_LAYOUTY = 210;

        GameOverStage(final Stage gameStage) {
            super();
            this.initModality(Modality.APPLICATION_MODAL);
            this.setOnCloseRequest(event -> gameStage.close());

            final AnchorPane pane = new AnchorPane();
            //noinspection SuspiciousNameCombination
            pane.prefWidth(SCENE_WIDTH);
            //noinspection SuspiciousNameCombination
            pane.prefHeight(SCENE_HEIGHT);

            final CustomizedButton homeButton = new CustomizedButton("HOME");
            final CustomizedButton restartButton = new CustomizedButton("RESTART");

            homeButton.setOnAction(event -> {
                this.close();
                gameStage.close();
                Platform.runLater(() -> new MainMenu().start(new Stage()));
            });

            restartButton.setOnAction(event -> Platform.runLater(() -> {
                try {
                    this.close();
                    gameStage.close();
                    new Game().start(new Stage());
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }));

            ViewManager.createLogo(LOGO_LAYOUTX, LOGO_LAYOUTY, "gameover.png", pane);
            ViewManager.setBackground("blue.png", SCENE_WIDTH, SCENE_HEIGHT, pane);

            homeButton.setLayoutX(BUTTON_LAYOUTX);
            homeButton.setLayoutY(HOME_BUTTON_LAYOUTY);
            restartButton.setLayoutX(BUTTON_LAYOUTX);
            restartButton.setLayoutY(RESTART_BUTTON_LAYOUTY);

            pane.getChildren().addAll(homeButton, restartButton);
            setScene(new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT));
        }
    }
}
