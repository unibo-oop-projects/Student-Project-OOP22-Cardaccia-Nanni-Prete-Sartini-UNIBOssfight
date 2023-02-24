package game;

import core.component.Renderer;
import core.component.Transform;
import core.level.Level;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;
import impl.level.LevelImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Vector2d;

import java.util.PriorityQueue;
import java.util.Queue;

public class Game extends Application {

    private Level currentLevel = new LevelImpl(new PlayerImpl(100, new Transform(new Vector2d(300, 300), 0) {
        @Override
        public void update() {

        }
    },50, 25));
    private boolean gameStarted = true;

    private Queue<PlayerImpl.Inputs> inputsQueue = new PriorityQueue<>();

    private PlayerImpl.Inputs currentCommand;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("UNIBOssfight");

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

        this.currentLevel.addEntity(new TmpEntityImpl(100, new Transform(new Vector2d(500, 500), 0) {
            @Override
            public void update() {

            }
        },50, 25));

        currentScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A -> inputsQueue.add(PlayerImpl.Inputs.LEFT);
                case D -> inputsQueue.add(PlayerImpl.Inputs.RIGHT);
                case SPACE -> inputsQueue.add(PlayerImpl.Inputs.SPACE);
            }
        });

        primaryStage.setScene(currentScene);
        primaryStage.show();

    }

    private void inputPoll(){
        this.currentCommand = this.inputsQueue.isEmpty() ? PlayerImpl.Inputs.EMPTY : this.inputsQueue.poll();
    }

    private void update(){
        if(this.currentCommand != PlayerImpl.Inputs.EMPTY){
            this.currentLevel.updatePlayer(this.currentCommand);
        }

    }

    private void render(GraphicsContext gc){
        this.currentLevel.renderEntities(gc);
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600, 600);

        gc.setFill(Color.WHITE);

        if(gameStarted){

            this.inputPoll();
            this.update();
            this.render(gc);

        }
    }
}
