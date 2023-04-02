package app.game;

import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.factory.BossFactoryImpl;
import app.impl.level.LevelImpl;
import app.ui.ConfirmBox;
import app.util.AppLogger;
import app.util.DataManager;
import app.util.Window;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Prova extends Application {

    private static final int FRAME_RATE = 60;
    private static final double FRAME_DURATION = 1000 / FRAME_RATE;
    private static final int MIN_WINDOW_HEIGHT = 600;
    private static final int MIN_WINDOW_WIDTH = 800;

    private final Level currentLevel;//new LevelImpl();
    private Group root = new Group();
    private Scene currentScene;
    private InputManager inputManager;
    private Image image;
    private Paint imagePattern;

    public Prova() throws Exception {
        currentLevel = new DataManager().loadLevel();//new LevelImpl();
    }

    @Override
    public void start(final Stage stage) {

        stage.setTitle("UNIBOssfight");

        stage.setOnCloseRequest(e -> {
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



        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinHeight(MIN_WINDOW_HEIGHT);
        stage.setMinWidth(MIN_WINDOW_WIDTH);

        this.root = new Group();

        //Creating a scene object
        currentScene = new Scene(root);

        this.inputManager = new InputManager(currentScene);

        this.currentScene.heightProperty().addListener(
                (observable, oldValue, newValue) -> Window.setHeight(currentScene.getHeight() - this.image.getHeight())
        );

        this.currentScene.widthProperty().addListener(
                (observable, oldValue, newValue) -> Window.setWidth(currentScene.getWidth())
        );


        currentScene.setOnMouseClicked(e -> this.currentLevel.playerShoot(new Point2D(e.getX(), e.getY())));

        //Adding scene to the stage
        stage.setScene(currentScene);

        //Displaying the contents of the stage
        stage.show();

        final Timeline tl = new Timeline(new KeyFrame(Duration.millis(FRAME_DURATION), e -> {
            if (!this.currentLevel.isOver())
                run();
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
}
