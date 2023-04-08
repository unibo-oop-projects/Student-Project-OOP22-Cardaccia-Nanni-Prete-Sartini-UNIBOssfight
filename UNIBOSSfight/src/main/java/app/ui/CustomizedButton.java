package app.ui;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class models a  custom Button with its own background and measures.
 */
public class CustomizedButton extends Button {

    private static final String FONT_PATH = "font.ttf";
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;"
            + " -fx-background-image: url('grey_button00.png');";
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;"
            + " -fx-background-image: url('blue_button00.png');";
    private static final int BUTTON_PRESSED_HEIGHT = 45;
    private static final int HEIGHT = 49;
    private static final int WIDTH = 190;
    private static final int FONT_SIZE = 23;
    private static final int OFFSET = 4;

    /**
     * Creates a new instance of the class CustomizedButton.
     *
     * @param text the text of the button
     */
    public CustomizedButton(final String text) {
        setText(text);
        setButtonFont();
        setPrefSize(WIDTH, HEIGHT);
        setStyle(BUTTON_FREE_STYLE);
        initButtonListeners();
    }

    private void setButtonFont() {
        try (InputStream is = ViewManager.class.getClassLoader()
                .getResourceAsStream(FONT_PATH)) {
            setFont(Font.loadFont(is, FONT_SIZE));
        } catch (final IOException e) {
            setFont(Font.font("Verdana", FONT_SIZE));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(BUTTON_PRESSED_HEIGHT);
        setLayoutY(getLayoutY() + OFFSET);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(BUTTON_PRESSED_HEIGHT);
        setLayoutY(getLayoutY() - OFFSET);
    }

    private void initButtonListeners() {
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }
}
