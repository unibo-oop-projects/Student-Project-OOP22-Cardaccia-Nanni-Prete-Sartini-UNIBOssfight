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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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
    private static final int BUTTONS_FONT_SIZE = 23;
    private static final int FONT_SIZE = 18;
    private static final int LEVEL_CHOICE_LAYOUT_X = 100;
    private static final int LEVEL_CHOICE_LAYOUT_Y = 75;
    private static final int HELP_LAYOUT_X = 25;
    private static final int HELP_LAYOUT_Y = 40;
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
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "It is needed to change the appearance of the stage"
                    + "passed as input in the start method of the MainMenu"
    )
    public ViewManager(final Stage stage) {
        stage.setResizable(false);

        this.mainStage = stage;
        this.menuButtons = new ArrayList<>();
        this.mainPane = new AnchorPane();
        final Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        stage.setScene(mainScene);

        createSubScenes();
        setLevelChoiceSubScene();
        setScoreSubScene();
        setHelpSubScene();
        createButtons();

        setBackground("blue.png", BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT, mainPane);
    }

    /**
     * Returns the main stage of the menu.
     *
     * @return the main stage
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "It is needed to let the MainMenu show"
                    + "the new appearance of the stage, set in here"
    )
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

        final InputStream is = ViewManager.class.getClassLoader()
                .getResourceAsStream(url);

        if (is != null) {
            final BackgroundImage background = new BackgroundImage(new Image(is,
                    width, height, false, true),
                    BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT, null);
            pane.setBackground(new Background(background));
        } else {
            AppLogger.getLogger().warning("Error occurred while loading background");
        }
    }

    /**
     * Sets the font of a list of labels.
     *
     * @param url the url where to find the font to apply
     * @param fontSize the font size
     * @param labels the labels on which the font is set
     */
    public static void setFont(final String url, final double fontSize,
                               final List<Label> labels) {
        labels.forEach(label -> {
            try (InputStream is = ViewManager.class.getClassLoader()
                    .getResourceAsStream(url)) {
                label.setFont(Font.loadFont(is, fontSize));
            } catch (final IOException e) {
                label.setFont(Font.font("Verdana", fontSize));
            }
        });
    }

    /**
     * Sets the font of the label.
     *
     * @param url the url where to find the font to apply
     * @param fontSize the font size
     * @param label the label on which the font is set
     */
    public static void setFont(final String url, final double fontSize, final Label label) {

        try (InputStream is = ViewManager.class.getClassLoader().getResourceAsStream(url)) {
            label.setFont(Font.loadFont(is, fontSize));
        } catch (final IOException e) {
            label.setFont(Font.font("Verdana", fontSize));
        }
    }

    private void createSubScenes() {
        this.scoreSubScene = new CustomizedSubScene();
        this.levelChoiceSubScene = new CustomizedSubScene();
        this.helpSubScene = new CustomizedSubScene();
    }

    private void setLevelChoiceSubScene() {
        final CustomizedButton level1 = new CustomizedButton("LEVEL 1");
        final CustomizedButton level2 = new CustomizedButton("LEVEL 2");

        level1.setStyle("-fx-background-color: transparent;"
                + " -fx-background-image: url('blue_button13.png');");
        level2.setStyle("-fx-background-color: transparent;"
                + " -fx-background-image: url('blue_button13.png');");

        this.levelChoiceSubScene.addLabel("Choose a level: ",
                LEVEL_CHOICE_LAYOUT_X, LEVEL_CHOICE_LAYOUT_Y, BUTTONS_FONT_SIZE);
        this.levelChoiceSubScene.addButtons(level1, level2);

        level1.setOnAction(event -> Platform.runLater(() -> {
            try {
                new Game("level1.json").start(new Stage());
                this.mainStage.close();
            } catch (final IOException e) {
                AppLogger.getLogger().severe(e.getMessage());
            }
        }));

        level2.setOnAction(event -> Platform.runLater(() -> {
            try {
                new Game("level2.json").start(new Stage());
                this.mainStage.close();
            } catch (final IOException e) {
                AppLogger.getLogger().severe(e.getMessage());
            }
        }));
    }

    private void setScoreSubScene() {

    }

    private void setHelpSubScene() {
        final String howToPlay = """
                Hi, welcome to UNIBOssfight!

                 Press 'D' to run forward,
                 press 'A' to run backward,
                 press 'space' to jump.

                 Use the mouse to point and
                 shoot the enemies, defeat
                 all of them and collect
                 all the coins to get
                 your degree.

                 Good Luck!\s""";

        this.helpSubScene.addLabel(howToPlay,
                HELP_LAYOUT_X, HELP_LAYOUT_Y, FONT_SIZE);
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
