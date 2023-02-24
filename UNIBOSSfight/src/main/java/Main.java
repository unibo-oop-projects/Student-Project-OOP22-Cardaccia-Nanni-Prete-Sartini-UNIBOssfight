import UI.Menu;
import game.Game;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Application.launch(Game.class, args);

        //Uncomment the following lines to see the temporary menu
        //Menu menu = new Menu();
        //Application.launch(Menu.class, args);

    }
}