package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Objects;

public class MainMenu extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
//    @Override
//    public void start(final Stage primaryStage) throws Exception {
//        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/mainMenu.fxml"));
//        final Scene scene = new Scene(Objects.requireNonNull(root), WIDTH, HEIGHT);
//        //final Label lbl = (Label) scene.lookup("#mainTitle");
//        primaryStage.setTitle("UNIBOssfight");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

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
