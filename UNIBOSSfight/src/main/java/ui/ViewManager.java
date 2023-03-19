package ui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    private final List<CostumizedButton> menuButtons;


    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane= new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        setBackground();
    }

    public Stage getMainStage() {
        return this.mainStage;
    }

    private void setBackground() {
        Image backgroundImage = new Image("blue.png", 256,
                256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void addMenuButton(CostumizedButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoresButton();
        createExitButton();
    }
    private void createStartButton() {
        CostumizedButton startButton = new CostumizedButton("PLAY");
        addMenuButton(startButton);
    }

    private void createScoresButton() {
        CostumizedButton scoreButton = new CostumizedButton("SCORES");
        addMenuButton(scoreButton);
    }

    private void createExitButton() {
        CostumizedButton exitButton = new CostumizedButton("EXIT");
        addMenuButton(exitButton);
    }
}
