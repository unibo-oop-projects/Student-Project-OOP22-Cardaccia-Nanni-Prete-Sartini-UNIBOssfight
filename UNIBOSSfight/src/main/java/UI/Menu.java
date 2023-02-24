package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

    Stage window;
    Button buttonAlert;
    Button buttonConfirm;
    Scene scene, scene1, scene2;


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
        window.setTitle("Examples");

        buttonAlert = new Button("Alert box");
        buttonAlert.setOnAction(e -> AlertBox.display("Alert box", "Some kind of alert"));

        buttonConfirm = new Button("Confirm box");
        buttonConfirm.setOnAction(e -> {
            var res = ConfirmBox.display("Confirm box",
                    "Are you sure you want to quit the game?");
            System.out.println(res);
        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(buttonConfirm, buttonAlert);
        scene = new Scene(layout1, 200, 200);
        window.setScene(scene);
        window.show();

    }
}
