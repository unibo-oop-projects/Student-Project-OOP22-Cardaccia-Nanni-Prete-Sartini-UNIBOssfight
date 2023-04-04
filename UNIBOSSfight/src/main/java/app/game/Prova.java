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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Prova extends Application {

    private static final double FRAME_RATE = 60;
    private static final double FRAME_DURATION = 1000 / FRAME_RATE;
    private static final int MIN_WINDOW_HEIGHT = 600;
    private static final int MIN_WINDOW_WIDTH = 800;
    private final Level currentLevel;//new LevelImpl();
    private Stage mainStage;
    private Group root = new Group();
    private Scene currentScene;
    private InputManager inputManager;
    private Image image;
    private Image bg;
    private Paint imagePattern;
    private Paint imagePattern2;
    private final AnchorPane anchorPane = new AnchorPane();
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);

    public Prova() throws Exception {
        currentLevel = new DataManager().loadLevel("bossLevel.json");
    }

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

        FileInputStream input = null;
        try {
            input = new FileInputStream("assets/ground/ground.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.image = new Image(input);

        try {
            this.bg = new Image(new FileInputStream("assets/background.png"),Window.getWidth(), Window.getHeight(), false, false);
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
                (observable, oldValue, newValue) -> Window.setHeight(currentScene.getHeight() - this.image.getHeight())
        );

        this.currentScene.widthProperty().addListener(
                (observable, oldValue, newValue) -> Window.setWidth(currentScene.getWidth())
        );

        this.gameOver.addListener(
                (observable, oldValue, newValue) -> new GameOverStage(this.mainStage).show()
        );

        this.currentScene.setOnMouseClicked(e -> this.currentLevel.playerShoot(new Point2D(e.getX(), e.getY())));

        //Adding scene to the stage
        this.mainStage.setScene(currentScene);

        //Displaying the contents of the stage
        this.mainStage.show();

        final Timeline tl = new Timeline(new KeyFrame(Duration.millis(FRAME_DURATION), e -> {
            if (!this.currentLevel.isOver()) {
                run();
            } else {
                gameOver.set(true);
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);

        tl.play();
        this.currentLevel.init();
    }

    private void inputPoll() {
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

        final Rectangle bgr = new Rectangle(0, 0, Window.getWidth(), Window.getHeight());

        // set fill for rectangle
        this.imagePattern = new ImagePattern(
                bg,
                -(this.currentLevel.getPlayerPosition().getX() / 10),
                0,
                Window.getHeight() * 16 / 9,
                Window.getHeight(),
                false
        );

        bgr.setFill(this.imagePattern);

        root.getChildren().add(bgr);


        root.getChildren().addAll(this.currentLevel.renderUniqueEntities());
        this.currentLevel.renderEntities().forEach(e -> root.getChildren().add(e));

        // create a Rectangle
        final Rectangle rect = new Rectangle(0, Window.getHeight(), Window.getWidth(), image.getHeight());

        // set fill for rectangle
        this.imagePattern = new ImagePattern(
                image,
                -this.currentLevel.getPlayerPosition().getX(),
                Window.getHeight() - image.getHeight(),
                image.getWidth(), image.getHeight(),
                false
        );

        rect.setFill(this.imagePattern);

        root.getChildren().add(rect);
    }

    // TODO creare copyOf del level
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    private void run() {
        inputPoll();
        update();
        render();
    }

    private class InputManager {
        private final Scene scene;
        private boolean isAPressed = false;
        private boolean isDPressed = false;
        private boolean isSpacePressed = false;


        public InputManager(final Scene scene) {
            this.scene = scene;

            this.scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case A -> this.isAPressed = true;
                    case D -> this.isDPressed = true;
                    case SPACE -> this.isSpacePressed = true;
                }
            });

            this.scene.setOnMouseMoved(e -> currentLevel.rotatePlayerWeapon(new Point2D(e.getX(), e.getY())));

            this.scene.setOnKeyReleased(e -> {
                switch (e.getCode()) {
                    case A -> this.isAPressed = false;
                    case D -> this.isDPressed = false;
                    case SPACE -> this.isSpacePressed = false;
                }
            });
        }
    }

    @Override
    public void stop() throws Exception {
            try {
                new DataManager().serializeLevel(this.currentLevel);
            } catch (Exception e) {
                AppLogger.getLogger().severe(e.getMessage());
            }
        super.stop();
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
                Platform.runLater(() -> {
                    ViewManager manager = new ViewManager();
                    new MainMenu().start(manager.getMainStage());
                });
            });

            restartButton.setOnAction(event -> Platform.runLater(() -> {
                try {
                    this.close();
                    gameStage.close();
                    new Prova().start(new Stage());
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }));

            ViewManager.createLogo(150, 15, "gameover.png", pane);
            ViewManager.setBackground("blue.png", SCENE_WIDTH, SCENE_HEIGHT, pane);

            homeButton.setLayoutX(155);
            homeButton.setLayoutY(140);
            restartButton.setLayoutX(155);
            restartButton.setLayoutY(210);

            pane.getChildren().addAll(homeButton, restartButton);
            setScene(new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT));
        }
    }
}
