package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

    Stage window;
    Button buttonAlert, buttonConfirm, button1, button2;
    Scene scene, scene1, scene2;


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.window = primaryStage;

        /* Code for switching scenes, first attempt*/

        //Button 1
        Label label1 = new Label("Welcome to UNIBOSSfight");
        button1 = new Button("Start");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 400, 300);

        //Button 2
        button2 = new Button("Go back to menu");
        button2.setOnAction(e -> window.setScene(scene));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600,400);

        /*Code for the alert box and the confirmation box*/

        //Button Alert
        buttonAlert = new Button("Alert box");
        buttonAlert.setOnAction(e -> AlertBox.display("Alert box",
                "Some kind of alert"));

        //Button Confirm
        buttonConfirm = new Button("Confirm box");
        buttonConfirm.setOnAction(e -> ConfirmBox.display("Confirm box",
                    "Are you sure you want to quit the game?"));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label1, button1, buttonConfirm, buttonAlert);
        scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.setTitle("UNIBOSSfight");
        window.show();

    }
}
