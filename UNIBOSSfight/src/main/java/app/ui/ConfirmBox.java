package app.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This utility class creates a new stage to interact
 * with the user while trying to close the game window.
 */
public final class ConfirmBox {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final int FONT_SIZE = 23;
    private static boolean answer;

    private ConfirmBox() {

    }

    /**
     * This static method is used to display the new stage.
     *
     * @param message the message to be shown
     * @return true if the "yes" button is clicked,
     * false if the "no" button is clicked
     */
    public static boolean display(final String message) {
        final Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(WIDTH);
        window.setHeight(HEIGHT);

        final Label label = new Label();
        label.setText(message);
        ViewManager.setFont("src/main/resources/font.ttf", FONT_SIZE, label);

        final CustomizedButton yesButton = new CustomizedButton("Yes");
        final CustomizedButton noButton = new CustomizedButton("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        final VBox layout = new VBox(15);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return answer;
    }

}
