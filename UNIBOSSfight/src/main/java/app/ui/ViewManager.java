package app.ui;

import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    private final List<CostumizedButton> menuButtons;
    private CostumizedSubScene scoreSubScene;
    private CostumizedSubScene levelChoiceSubScene;
    private CostumizedSubScene helpSubScene;
    private CostumizedSubScene exitSubScene;
    private CostumizedSubScene sceneToHide;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane= new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        setBackground();
        createLogo();
    }

    private void showSubScene(CostumizedSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {
        scoreSubScene = new CostumizedSubScene();
        levelChoiceSubScene = new CostumizedSubScene();
        helpSubScene = new CostumizedSubScene();
        exitSubScene = new CostumizedSubScene();

        mainPane.getChildren().addAll(scoreSubScene, levelChoiceSubScene,
                helpSubScene, exitSubScene);
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
        createLevelButton();
        createHelpButton();
        createExitButton();
    }

    private void createStartButton() {
        CostumizedButton startButton = new CostumizedButton("PLAY");
        addMenuButton(startButton);
    }

    private void createScoresButton() {
        CostumizedButton scoreButton = new CostumizedButton("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(event -> showSubScene(scoreSubScene));
    }

    private void createLevelButton() {
        CostumizedButton levelButton = new CostumizedButton("LEVEL");
        addMenuButton(levelButton);
        levelButton.setOnAction(event -> showSubScene(levelChoiceSubScene));
    }

    private void createHelpButton() {
        CostumizedButton helpButton = new CostumizedButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }

    private void createExitButton() {
        CostumizedButton exitButton = new CostumizedButton("EXIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(event -> mainStage.close());
    }

    private void createLogo() {
        ImageView logo = new ImageView("UNIBOssfight-1.png");
        logo.setLayoutX(5);
        logo.setLayoutY(5);
        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow()));
        logo.setOnMouseExited(event -> logo.setEffect(null));
        mainPane.getChildren().add(logo);
    }
}
