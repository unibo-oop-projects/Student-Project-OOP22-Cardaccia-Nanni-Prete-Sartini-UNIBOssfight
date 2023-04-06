package app.ui;

import javafx.animation.FadeTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
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
    private final AnchorPane root = (AnchorPane) this.getRoot();


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
        this.root.setBackground(new Background(image));

        setLayoutX(LAYOUTX);
        setLayoutY(LAYOUTY);

    }

    /**
     * Adds the buttons to the sub scene.
     * @param button1 the first button to be added
     * @param button2 the second button to be added
     */
    public void addButtons(final CustomizedButton button1,
                           final CustomizedButton button2) {
        button1.setLayoutX(23);
        button1.setLayoutY(LAYOUTX / 2.0);
        button2.setLayoutX(WIDTH / 2.0);
        button2.setLayoutY(LAYOUTX / 2.0);

        this.root.getChildren().addAll(button1, button2);
    }

    /**
     * Adds the label to the sub scene.
     *
     * @param text the text to be added
     */
    public void addLabel(final String text) {
        final Label label = new Label(text);
        label.setLayoutX(LAYOUTX / 3.0);
        label.setLayoutY(LAYOUTY / 2.0);
        ViewManager.setFont("src/main/resources/font.ttf", 23, label);
        this.root.getChildren().add(label);
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
