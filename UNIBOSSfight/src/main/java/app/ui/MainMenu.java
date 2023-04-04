package app.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class creates the main menu of the game.
 */
public class MainMenu extends Application {

    /**
     * The method used to show the primary stage of this application.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    public void start(Stage primaryStage) {
        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
