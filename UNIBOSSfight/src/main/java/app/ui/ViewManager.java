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

/**
 * This class is used to manage the appearance of the user interface
 * by adding buttons and sub scenes to the main menu of the game.
 */
public class ViewManager {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BACKGROUND_WIDTH = 256;
    private static final int BACKGROUND_HEIGHT = 256;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private static final int MENU_BUTTONS_START_X = 50;
    private static final int MENU_BUTTONS_START_Y = 80;
    private final List<CustomizedButton> menuButtons;
    private CustomizedSubScene scoreSubScene;
    private CustomizedSubScene levelChoiceSubScene;
    private CustomizedSubScene helpSubScene;
    private CustomizedSubScene exitSubScene;
    private CustomizedSubScene sceneToHide;

    /**
     * Creates a new instance of the class ViewManager.
     */
    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        setBackground("blue.png", BACKGROUND_WIDTH, BACKGROUND_HEIGHT, mainPane);
        //createLogo(5, 5, "UNIBOssfight.png", mainPane);
    }

    /**
     * Returns the main stage.
     *
     * @return the main stage
     */
    public Stage getMainStage() {
        return this.mainStage;
    }

    /**
     * Creates the logo of the game.
     *
     * @param layoutX the x layout of the logo
     * @param layoutY the y layout of the logo
     * @param url the url of the image of the logo
     * @param pane the pane in which the logo is added
     */
    public static void createLogo(final double layoutX, final double layoutY,
                                  final String url, final AnchorPane pane) {
        ImageView logo = new ImageView(url);
        logo.setLayoutX(layoutX);
        logo.setLayoutY(layoutY);
        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow()));
        logo.setOnMouseExited(event -> logo.setEffect(null));
        pane.getChildren().add(logo);
    }

    /**
     * Sets the background of the scenes.
     *
     * @param url the image of the background
     * @param width the width of the background
     * @param height the height of the background
     * @param pane the pane in which the background will be added
     */
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
        scoreSubScene = new CustomizedSubScene();
        levelChoiceSubScene = new CustomizedSubScene();
        helpSubScene = new CustomizedSubScene();
        exitSubScene = new CustomizedSubScene();

        mainPane.getChildren().addAll(scoreSubScene, levelChoiceSubScene,
                helpSubScene, exitSubScene);
    }

    private void showSubScene(final CustomizedSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void addMenuButton(final CustomizedButton button) {
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
        CustomizedButton startButton = new CustomizedButton("PLAY");
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
        CustomizedButton scoreButton = new CustomizedButton("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(event -> showSubScene(scoreSubScene));
    }

    private void createLevelButton() {
        CustomizedButton levelButton = new CustomizedButton("LEVEL");
        addMenuButton(levelButton);
        levelButton.setOnAction(event -> showSubScene(levelChoiceSubScene));
    }

    private void createHelpButton() {
        CustomizedButton helpButton = new CustomizedButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }

    private void createExitButton() {
        CustomizedButton exitButton = new CustomizedButton("QUIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(event -> mainStage.close());
    }
}
