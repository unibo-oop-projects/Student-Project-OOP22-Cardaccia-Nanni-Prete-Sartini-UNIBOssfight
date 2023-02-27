package game;

import UI.ConfirmBox;
import core.component.Transform;
import core.entity.Entity;
import core.level.Level;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;
import impl.level.LevelImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                run(gc);
            }
        };

        timer.start();

        Scene currentScene = new Scene(new StackPane(canvas));

        this.currentLevel.addEntity(new TmpEntityImpl(new Transform(new Point2D(500, 500), 0) {
            @Override
            public void update() {

            }
        },50, 25,  "testImage2.png"));

        currentScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A -> inputsQueue.add(Entity.Inputs.LEFT);
                case D -> inputsQueue.add(Entity.Inputs.RIGHT);
                case SPACE -> inputsQueue.add(Entity.Inputs.SPACE);
            }
        });

        this.gameWindow.setScene(currentScene);
        this.gameWindow.show();

    }

    private void inputPoll(){
        this.currentCommand = this.inputsQueue.isEmpty() ? Entity.Inputs.EMPTY : this.inputsQueue.poll();
    }

    private void update(){
            this.currentLevel.updatePlayer(this.currentCommand);

    }

    private void render(GraphicsContext gc){
        this.currentLevel.renderEntities(gc);
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.rgb(26, 228, 255));

        gc.fillRect(0,0,600, 600);



        if(gameStarted){

            this.inputPoll();
            this.update();
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
