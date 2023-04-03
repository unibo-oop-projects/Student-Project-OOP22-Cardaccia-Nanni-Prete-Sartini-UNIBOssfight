package app.ui;

import javafx.animation.TranslateTransition;
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

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private final String backgroundImage = "blue_panel.png";
    private boolean isHidden = true;

    /**
     * Creates a new instance of the class CustomizedSubScene.
     */
    public CustomizedSubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        prefHeight(HEIGHT);
        prefWidth(WIDTH);
        BackgroundImage image = new BackgroundImage(new Image(backgroundImage, WIDTH, HEIGHT,
                false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(180);

    }

    /**
     * This method is used to create the transition for the sub scene.
     */
    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (isHidden) {
            transition.setToX(-676);
            this.isHidden = false;
        } else {
            transition.setToX(0);
            this.isHidden = true;
        }
        transition.play();
    }
}
