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
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Prova extends Application {

    Stage gameWindow;

    private LevelImpl currentLevel = new LevelImpl(new PlayerImpl(new Transform(new Point2D(300, 300), 0) {
        @Override
        public void update() {

        }
    },250, 200, "testImage.png"));
    private boolean gameStarted = true;

    private Queue<Entity.Inputs> inputsQueue = new PriorityQueue<>();

    private Entity.Inputs currentCommand;
    private boolean isAPressed;
    private boolean isDPressed;
    private boolean isSpacePressed;

    Group root = new Group();
    Scene currentScene;
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("UNIBOssfight");
        //Creating an image
        Image image = new Image(new FileInputStream("assets/gnu.png"));

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(16), e -> run()));
        tl.setCycleCount(Animation.INDEFINITE);

        this.currentLevel.addEntity(new TmpEntityImpl(new Transform(new Point2D(500, 500), 0) {
            @Override
            public void update() {

            }
        },50, 50,  "goomba.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        Rotate rt = new Rotate();
        rt.setAngle(10);

        imageView.getTransforms().add(rt);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        this.root = new Group();

        this.root.getChildren().add(imageView);

        //Creating a scene object
        currentScene = new Scene(root);

        currentScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A -> this.isAPressed = true;
                case D -> this.isDPressed = true;
                case SPACE -> this.isSpacePressed = true;
            }
        });

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
    }

    private void run() {
        inputPoll();
        update();
        render();
    }
}