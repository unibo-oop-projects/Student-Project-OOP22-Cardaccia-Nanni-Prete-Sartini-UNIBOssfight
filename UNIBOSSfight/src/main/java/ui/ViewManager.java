package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewManager {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager() {
        mainPane= new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
    }

    public Stage getMainStage() {
        return this.mainStage;
    }

    private void createButton() {
        Button button = new Button();
        mainPane.getChildren().add(button);
    }
}
