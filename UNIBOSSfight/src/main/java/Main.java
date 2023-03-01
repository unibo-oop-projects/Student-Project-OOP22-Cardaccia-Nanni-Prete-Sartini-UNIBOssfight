import UI.Menu;
import game.Game;
import game.Prova;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Prova game = new Prova();
        Application.launch(Prova.class, args);

        //Uncomment the following lines to see the temporary menu
        Menu menu = new Menu();
        //Application.launch(Menu.class, args);

    }
}