package app;

import app.game.Game;
import javafx.application.Application;

/**
 * Main class of the project.
 */
public final class Main {

    private Main() { }

    /**
     * Entry point of the application.
     * @param args ...
     */
    public static void main(final String[] args) {
        //Application.launch(MainMenu.class, args);
        Application.launch(Game.class, args);
    }
}
