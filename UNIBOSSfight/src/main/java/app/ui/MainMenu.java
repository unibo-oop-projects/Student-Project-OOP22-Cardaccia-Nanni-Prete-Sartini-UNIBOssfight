package app.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public void start(Stage primaryStage) {
        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void run(final String[] args) {
        launch(args);
    }
}
