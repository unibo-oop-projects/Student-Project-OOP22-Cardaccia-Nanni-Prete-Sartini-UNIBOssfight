package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class ConfirmBox {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static boolean answer;

    private ConfirmBox() {}

    public static boolean display(final String message) {
        final Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(WIDTH);
        window.setMinHeight(HEIGHT);

        final Label label = new Label();
        label.setText(message);

        final Button yesButton = new Button("Yes");
        final Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            System.exit(0);
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        final VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return answer;
    }

}
