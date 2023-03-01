package game;

import UI.ConfirmBox;
import core.component.Transform;
import core.entity.Entity;
import core.level.Level;
import impl.entity.EnemyImpl;
import impl.entity.Platform;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;
import impl.level.LevelImpl;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.PriorityQueue;
import java.util.Queue;

public class Game extends Application {

    Stage gameWindow;

    private Level currentLevel = new LevelImpl(new PlayerImpl(new Transform(new Point2D(300, 300), 0) {
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

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.gameWindow = primaryStage;
        this.gameWindow.setTitle("UNIBOssfight");
        /*this.gameWindow.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });*/

        Canvas canvas = new Canvas(600, 600);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        /*AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                run(gc);
            }
        };*/

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(16), e -> run(gc)));
        tl.setCycleCount(Animation.INDEFINITE);
        //timer.start();

        Scene currentScene = new Scene(new StackPane(canvas));

        this.currentLevel.addEntity(new EnemyImpl(new Transform(new Point2D(500, 500), 0) {
            @Override
            public void update() {

            }
        },50, 50,  "goomba.png"));
        this.currentLevel.addEntity(new Platform(new Transform(new Point2D(120, 500), 0) {
            @Override
            public void update() {

            }
        },50, 50,  null));
        this.currentLevel.addEntity(new Platform(new Transform(new Point2D(580, 500), 0) {
            @Override
            public void update() {

            }
        },50, 50,  null));

        /*currentScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A -> inputsQueue.add(Entity.Inputs.LEFT);
                case D -> inputsQueue.add(Entity.Inputs.RIGHT);
                case SPACE -> inputsQueue.add(Entity.Inputs.SPACE);
            }
        });*/
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

        this.gameWindow.setScene(currentScene);
        this.gameWindow.show();

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

    private void render(GraphicsContext gc){
        this.currentLevel.renderEntities(gc);
    }

    private void collision() {
        this.currentLevel.collision();
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.rgb(26, 228, 255));

        gc.fillRect(0,0,600, 600);



        if(gameStarted){

            this.inputPoll();
            this.update();
            this.collision();
            this.render(gc);

        }
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Confirm box",
                "Are you sure you want to quit the game?");
        if (answer) {
            gameWindow.close();
        }

    }

}
