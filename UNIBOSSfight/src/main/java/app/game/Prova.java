package app.game;

import app.impl.component.TransformImpl;
import app.impl.entity.Coin;
import app.impl.entity.HarmfulObstacle;
import app.impl.entity.Wall;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import app.core.component.Renderer;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import app.impl.entity.PlayerImpl;
import app.impl.level.LevelImpl;
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
import app.ui.ConfirmBox;
import app.util.AbstractEntityDeserializer;
import app.util.PlayerImplDeserializer;
import app.util.RendererDeserializer;
import app.util.Window;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Prova extends Application {

    private static final int FRAME_RATE = 60;
    private static final double FRAME_DURATION = 1000 / FRAME_RATE;
    private static final int MIN_WINDOW_HEIGHT = 600;
    private static final int MIN_WINDOW_WIDTH = 800;

    private final LevelImpl currentLevel = new DataManager().loadLevel();//new LevelImpl();
    private Group root = new Group();
    private Scene currentScene;
    private InputManager inputManager;
    private Image image;
    private Paint imagePattern;

    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
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
            run();
        }));
        tl.setCycleCount(Animation.INDEFINITE);


        /*this.currentLevel.addEntity(
            new Wall(new TransformImpl(
                    new Point2D(this.currentLevel.getPlayerPosition().getX() + 300, Window.getHeight())
                    , 0),
                    50, 50, "wall.png")
        );

        this.currentLevel.addEntity(
                new Wall(new TransformImpl(
                        new Point2D(this.currentLevel.getPlayerPosition().getX() + 900, Window.getHeight() / 2.0)
                        , 0),
                        50, 50, "wall.png")
        );

        this.currentLevel.addEntity(
                new Coin(new TransformImpl(
                        new Point2D(this.currentLevel.getPlayerPosition().getX() + 600, Window.getHeight() - 10)
                        , 0),
                        120, 120, "coin.png")
        );

        this.currentLevel.addEntity(
                new HarmfulObstacle(new TransformImpl(
                        new Point2D(this.currentLevel.getPlayerPosition().getX() + 1400, Window.getHeight() - 10)
                        , 0),
                        120, 120, "spine.png")
        );*/

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
        root.getChildren().add(this.currentLevel.renderPlayer());
        root.getChildren().add(this.currentLevel.renderWeapon());
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
    public LevelImpl getCurrentLevel() {
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
                    case A:
                        this.isAPressed = true;
                        break;
                    case D:
                        this.isDPressed = true;
                        break;
                    case SPACE:
                        this.isSpacePressed = true;
                        break;
                }
            });

            this.scene.setOnMouseMoved(e -> currentLevel.rotatePlayerWeapon(new Point2D(e.getX(), e.getY())));

            this.scene.setOnKeyReleased(e -> {
                switch (e.getCode()) {
                    case A:
                        this.isAPressed = false;
                        break;
                    case D:
                        this.isDPressed = false;
                        break;
                    case SPACE:
                        this.isSpacePressed = false;
                        break;
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

    @Override
    public void stop() throws Exception {
            try {
                new DataManager().serializeLevel(this.currentLevel);
            } catch (Exception e) {
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }
}
