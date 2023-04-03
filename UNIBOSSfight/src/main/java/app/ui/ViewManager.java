package app.ui;

import app.game.Prova;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private final static int MENU_BUTTONS_START_X = 50;
    private final static int MENU_BUTTONS_START_Y = 80;
    private final List<CostumizedButton> menuButtons;
    private CostumizedSubScene scoreSubScene;
    private CostumizedSubScene levelChoiceSubScene;
    private CostumizedSubScene helpSubScene;
    private CostumizedSubScene exitSubScene;
    private CostumizedSubScene sceneToHide;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        setBackground("blue.png", 256, 256, mainPane);
        //createLogo(5, 5, "UNIBOssfight.png", mainPane);
    }

    public Stage getMainStage() {
        return this.mainStage;
    }

    public static void createLogo(final double layoutX, final double layoutY,
                                  final String url, final AnchorPane pane) {
        ImageView logo = new ImageView(url);
        logo.setLayoutX(layoutX);
        logo.setLayoutY(layoutY);
        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow()));
        logo.setOnMouseExited(event -> logo.setEffect(null));
        pane.getChildren().add(logo);
    }

    public static void setBackground(final String url, final double width,
                                     final double height, final AnchorPane pane) {
        BackgroundImage background = new BackgroundImage(new Image(url, width,
                height, false, true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }
    private void createSubScenes() {
        scoreSubScene = new CostumizedSubScene();
        levelChoiceSubScene = new CostumizedSubScene();
        helpSubScene = new CostumizedSubScene();
        exitSubScene = new CostumizedSubScene();

        mainPane.getChildren().addAll(scoreSubScene, levelChoiceSubScene,
                helpSubScene, exitSubScene);
    }

    private void showSubScene(CostumizedSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
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

        startButton.setOnAction(event -> Platform.runLater(() -> {
            try {
                new Prova().start(new Stage());
                this.mainStage.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
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
        CostumizedButton exitButton = new CostumizedButton("QUIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(event -> mainStage.close());
    }
}
