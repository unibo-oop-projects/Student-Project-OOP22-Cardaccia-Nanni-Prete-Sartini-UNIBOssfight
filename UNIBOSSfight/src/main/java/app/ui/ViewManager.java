package app.ui;

import app.game.Game;
import app.util.AppLogger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private static final int MENU_BUTTONS_START_X = 50;
    private static final int MENU_BUTTONS_START_Y = 80;
    private final Stage mainStage;
    private final AnchorPane mainPane;
    private final List<CustomizedButton> menuButtons;
    private CustomizedSubScene scoreSubScene;
    private CustomizedSubScene levelChoiceSubScene;
    private CustomizedSubScene helpSubScene;
    private CustomizedSubScene currentScene;


    /**
     * Creates a new instance of the class ViewManager.
     *
     * @param stage the stage of the menu
     */
    public ViewManager(final Stage stage) {
        this.mainStage = stage;
        this.mainStage.setResizable(false);
        this.menuButtons = new ArrayList<>();
        this.mainPane = new AnchorPane();
        final Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        this.mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        setBackground("blue.png", BACKGROUND_WIDTH, BACKGROUND_HEIGHT, mainPane);
        //createLogo(325, 150, "UNIBOssfight.png", mainPane);
    }

    /**
     * Returns the main stage of the menu.
     *
     * @return the main stage
     */
    public Stage getMainStage() {
        return this.mainStage;
    }

    /**
     * Creates a logo with an image.
     *
     * @param layoutX the x layout of the logo
     * @param layoutY the y layout of the logo
     * @param url the url of the image of the logo
     * @param pane the pane in which the logo is added
     */
    public static void createLogo(final double layoutX, final double layoutY,
                                  final String url, final AnchorPane pane) {
        final ImageView logo = new ImageView(url);
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
        final BackgroundImage background = new BackgroundImage(new Image(url, width,
                height, false, true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }

//    public static void setBackground(final String url, final double width,
//                                     final double height, final List<Label> labels) {
//        labels.forEach(label -> {
//            final BackgroundImage background = new BackgroundImage(new Image(url, width,
//                    height, false, true),
//                    BackgroundRepeat.NO_REPEAT,
//                    BackgroundRepeat.NO_REPEAT,
//                    BackgroundPosition.CENTER, null);
//            label.setBackground(new Background(background));
//        });
//    }

    /**
     * Sets the font of the labels.
     *
     * @param url the url where to find the font to apply
     * @param fontSize the font size
     * @param labels the label on which the font is set
     */
    public static void setFont(final String url, final double fontSize, final List<Label> labels) {
        labels.forEach(label -> {
            try {
                label.setFont(Font.loadFont(new FileInputStream(url), fontSize));
            } catch (final FileNotFoundException e) {
                label.setFont(Font.font("Verdana", fontSize));
            }
        });
    }
    private void createSubScenes() {
        this.scoreSubScene = new CustomizedSubScene();
        this.levelChoiceSubScene = new CustomizedSubScene();
        this.helpSubScene = new CustomizedSubScene();
    }

    private void showSubScene(final CustomizedSubScene subScene) {
        this.mainPane.getChildren().remove(currentScene);
        subScene.fadeInSubScene();
        this.mainPane.getChildren().add(subScene);
        this.currentScene = subScene;
    }

    private void addMenuButton(final CustomizedButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        this.menuButtons.add(button);
        this.mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoresButton();
        createLevelButton();
        createHelpButton();
        createExitButton();
    }

    private void createStartButton() {
        final CustomizedButton startButton = new CustomizedButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(event -> Platform.runLater(() -> {
            try {
                new Game().start(new Stage());
                this.mainStage.close();
            } catch (final IOException e) {
                AppLogger.getLogger().severe(e.getMessage());
            }
        }));
    }

    private void createScoresButton() {
        final CustomizedButton scoreButton = new CustomizedButton("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(event -> showSubScene(scoreSubScene));
    }

    private void createLevelButton() {
        final CustomizedButton levelButton = new CustomizedButton("LEVEL");
        addMenuButton(levelButton);
        levelButton.setOnAction(event -> showSubScene(levelChoiceSubScene));
    }

    private void createHelpButton() {
        final CustomizedButton helpButton = new CustomizedButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }

    private void createExitButton() {
        final CustomizedButton exitButton = new CustomizedButton("QUIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(event -> mainStage.close());
    }
}
