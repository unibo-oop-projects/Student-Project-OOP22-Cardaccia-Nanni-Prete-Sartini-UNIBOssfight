package app.ui;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This class models a custom SubScene with its own background and measures.
 */
public class CustomizedSubScene extends SubScene {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int LAYOUTX = 325;
    private static final int LAYOUTY = HEIGHT / 2;
    private static final String BACKGROUND_IMAGE = "blue_panel.png";

    /**
     * Creates a new instance of the class CustomizedSubScene.
     */
    public CustomizedSubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);

        prefHeight(HEIGHT);
        prefWidth(WIDTH);

        final BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,
                WIDTH, HEIGHT, false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        final AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));

        setLayoutX(LAYOUTX);
        setLayoutY(LAYOUTY);
    }

    /**
     * This method is used to create the fade-in transition for the sub scene.
     */
    public void fadeInSubScene() {
        final var transition = new FadeTransition(Duration.millis(600));
        transition.setNode(this);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }

    public void addImage(final String url) throws FileNotFoundException {
        final InputStream stream = new FileInputStream(url);
        final Image image = new Image(stream);
        final ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
        final Group root = new Group(imageView);
    }
}
