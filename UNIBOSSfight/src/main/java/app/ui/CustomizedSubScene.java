package app.ui;

import app.util.AppLogger;
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
import java.io.InputStream;

/**
 * This class models a custom SubScene with its own background and measures.
 */
public class CustomizedSubScene extends SubScene {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 350;
    private static final int LAYOUTX = 300;
    private static final int LAYOUTY = 150;
    private static final int BUTTONS_LAYOUT_X = 135;
    private static final int BUTTON1_LAYOUT_Y = 125;
    private static final int BUTTON2_LAYOUT_Y = 195;
    private static final String BACKGROUND_IMAGE = "blue_panel.png";
    private final AnchorPane root = (AnchorPane) this.getRoot();


    /**
     * Creates a new instance of the class CustomizedSubScene.
     */
    public CustomizedSubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        prefHeight(WIDTH);

        final InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(BACKGROUND_IMAGE);
        if (inputStream != null) {
            final BackgroundImage image = new BackgroundImage(new Image(inputStream,
                    WIDTH, HEIGHT, false, true), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            this.root.setBackground(new Background(image));
        } else {
            AppLogger.getLogger().warning("Error occurred while loading background");
        }

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
        button1.setLayoutX(BUTTONS_LAYOUT_X);
        button1.setLayoutY(BUTTON1_LAYOUT_Y);
        button2.setLayoutX(BUTTONS_LAYOUT_X);
        button2.setLayoutY(BUTTON2_LAYOUT_Y);
        this.root.getChildren().addAll(button1, button2);
    }

    /**
     * Adds the label to the sub scene.
     *
     * @param text the text to be added
     * @param layoutX the x layout of the label
     * @param layoutY the y layout of the label
     * @param fontSize the text's font size
     */
    public void addLabel(final String text, final double layoutX,
                         final double layoutY, final double fontSize) {
        final Label label = new Label(text);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        ViewManager.setFont("font.ttf", fontSize, label);
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
