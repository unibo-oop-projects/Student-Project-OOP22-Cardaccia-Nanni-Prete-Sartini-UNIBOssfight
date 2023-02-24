package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

    Stage window;
    Button button;
    Scene scene1, scene2;


    @Override
    public void start(Stage primaryStage) throws Exception {

        /* Code for switching scenes, first attempt*/

        /* this.window = primaryStage;

        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button 2
        Button button2 = new Button("Go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600,300);

        window.setScene(scene1);
        window.setTitle("Title");
        window.show(); */

        /*Code for the alert box*/

        this.window = primaryStage;
        window.setTitle("Trying alert boxes");

        button = new Button("Click me");
        button.setOnAction(e -> AlertBox.display("Title of the window", "Some kind of alert"));

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300,250);
        window.setScene(scene);
        window.show();

    }
}
