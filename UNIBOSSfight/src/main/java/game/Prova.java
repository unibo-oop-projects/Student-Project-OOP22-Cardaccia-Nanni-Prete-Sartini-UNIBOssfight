package game;

import core.component.Transform;
import core.entity.Entity;
import impl.entity.TmpEntityImpl;
import impl.level.LevelImpl;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Prova extends Application {

    private static final int FRAME_RATE = 30;
    private static final double FRAME_DURATION = 1000 / FRAME_RATE;

    private LevelImpl currentLevel = new LevelImpl();
    private Group root = new Group();
    private Scene currentScene;
    private InputManager inputManager;

    @Override
    public void start(final Stage stage) {

        stage.setTitle("UNIBOssfight");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        this.root = new Group();

        //Creating a scene object
        currentScene = new Scene(root);

        this.inputManager = new InputManager(currentScene);

        this.currentScene.heightProperty().addListener(
                (observable, oldValue, newValue) -> Window.setHeight(currentScene.getHeight())
        );

        this.currentScene.widthProperty().addListener(
                (observable, oldValue, newValue) -> Window.setWidth(currentScene.getWidth())
        );


        currentScene.setOnMouseClicked(e -> this.currentLevel.playerShoot(new Point2D(e.getX(), e.getY())));


        //Adding scene to the stage
        stage.setScene(currentScene);

        //Displaying the contents of the stage
        stage.show();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(FRAME_DURATION), e -> {
            run();
        }));
        tl.setCycleCount(Animation.INDEFINITE);

        this.currentLevel.addEntity(
                new TmpEntityImpl(
                        new Transform(new Point2D(500, Window.getHeight()), 0),
                        50,
                        50,
                        "goomba.png"
                )
        );

        tl.play();
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
    }

    private void render() {
        this.root.getChildren().clear();
        this.currentLevel.renderEntities().forEach(e -> root.getChildren().add(e));
        root.getChildren().add(this.currentLevel.renderPlayer());
        root.getChildren().add(this.currentLevel.renderWeapon());

        FileInputStream input = null;
        try {
            input = new FileInputStream("assets/ground2.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // create a image
        Image image = new Image(input);


        // create ImagePattern
        ImagePattern imagePattern = new ImagePattern(
                image,
                -this.currentLevel.getPlayerPosition().getX(),
                Window.getHeight() - image.getHeight(),
                image.getWidth(), image.getHeight(),
                false
        );

        // create a Rectangle
        Rectangle rect = new Rectangle(0, Window.getHeight() - image.getHeight(), Window.getWidth(), image.getHeight());

        // set fill for rectangle
        rect.setFill(imagePattern);

        root.getChildren().add(rect);
    }

    private void collision() {
        this.currentLevel.collision();
    }

    public LevelImpl getCurrentLevel() {
        return this.currentLevel;
    }

    private void run() {
        inputPoll();
        update();
        collision();
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

        public boolean isAPressed() {
            return this.isAPressed;
        }

        public boolean isDPressed() {
            return this.isDPressed;
        }

        public boolean isSpacePressed() {
            return this.isSpacePressed;
        }
    }
}
