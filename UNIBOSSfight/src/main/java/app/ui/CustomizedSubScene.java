package app.ui;

import javafx.animation.FadeTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

/**
 * This class models a custom SubScene with its own background and measures.
 */
public class CustomizedSubScene extends SubScene {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 350;
    private static final int LAYOUTX = 300;
    private static final int LAYOUTY = 150;
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
}
