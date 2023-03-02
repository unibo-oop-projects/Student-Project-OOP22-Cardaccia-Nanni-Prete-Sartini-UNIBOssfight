package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;

import core.component.Transform;
import core.entity.Entity;
import core.level.Level;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;
import impl.level.LevelImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Window;

public class Prova extends Application {

    private LevelImpl currentLevel = new LevelImpl(new PlayerImpl(new Transform(new Point2D(0, 300), 0) {
        @Override
        public void update() {

        }
    },250, 200, "testImage.png"));

    private Queue<Entity.Inputs> inputsQueue = new PriorityQueue<>();
    private boolean isAPressed;
    private boolean isDPressed;
    private boolean isSpacePressed;

    private Group root = new Group();
    private Scene currentScene;

    private double windowHeight;

    private double windowWidth;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("UNIBOssfight");

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            run();}));
        tl.setCycleCount(Animation.INDEFINITE);

        this.currentLevel.addEntity(new TmpEntityImpl(new Transform(new Point2D(500, 500), 0) {
            @Override
            public void update() {

            }
        },50, 50,  "goomba.png"));

        /*this.currentLevel.addEntity(new TmpEntityImpl(new Transform(new Point2D(200, 500), 0) {
            @Override
            public void update() {

            }
        },50, 50,  "goomba.png"));**/

        //Setting the image vie

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Window.setHeight(stage.getHeight());
            }
        });

        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Window.setWidth(stage.getWidth());
            }
        });

        this.root = new Group();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        //Creating a scene object
        currentScene = new Scene(root);

        currentScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A -> this.isAPressed = true;
                case D -> this.isDPressed = true;
                case SPACE -> this.isSpacePressed = true;
            }
        });

        currentScene.setOnMouseMoved(e -> this.currentLevel.rotatePlayerWeapon(new Point2D(e.getX(), e.getY())));

        currentScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case A -> this.isAPressed = false;
                case D -> this.isDPressed = false;
                case SPACE -> this.isSpacePressed = false;
            }
        });


        //Adding scene to the stage
        stage.setScene(currentScene);

        //Displaying the contents of the stage
        stage.show();

        tl.play();
    }

    private void inputPoll(){
        //this.currentCommand = this.inputsQueue.isEmpty() ? Entity.Inputs.EMPTY : this.inputsQueue.poll();
        if(this.inputsQueue.isEmpty())
            this.inputsQueue.add(Entity.Inputs.EMPTY);
    }

    private void update(){
        this.currentLevel.updateEntities();
        if(this.isSpacePressed)
            this.currentLevel.updatePlayer(Entity.Inputs.SPACE);
        if(this.isDPressed)
            this.currentLevel.updatePlayer(Entity.Inputs.RIGHT);
        if(this.isAPressed)
            this.currentLevel.updatePlayer(Entity.Inputs.LEFT);
        //if(!(this.isSpacePressed || this.isDPressed || this.isAPressed))
        this.currentLevel.updatePlayer(Entity.Inputs.EMPTY);


    }

    private void render(){
        this.root.getChildren().clear();
        this.currentLevel.renderEntities().forEach(e -> root.getChildren().add(e));
        root.getChildren().add(this.currentLevel.renderPlayer());
        root.getChildren().add(this.currentLevel.renderWeapon());

        FileInputStream input = null;
        try {
            input = new FileInputStream("assets/ground.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // create a image
        Image image = new Image(input);

        // create ImagePattern
        ImagePattern image_pattern = new ImagePattern(image, -this.currentLevel.getPlayerPosition().getX(), 60,
                200, 200, false);

        // create a Rectangle
        Rectangle rect = new Rectangle(0, Window.getHeight() - 200, Window.getWidth(), 200);

        // set fill for rectangle
        rect.setFill(image_pattern);

        root.getChildren().add(rect);
    }

    private void run() {

        inputPoll();
        update();
        render();
    }
}