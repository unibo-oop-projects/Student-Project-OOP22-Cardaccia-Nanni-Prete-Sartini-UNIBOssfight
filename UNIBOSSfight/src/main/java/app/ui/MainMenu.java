package app.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class creates the main menu of the game.
 */
public class MainMenu extends Application {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) {
        new ViewManager(primaryStage).getMainStage().show();
    }
}
