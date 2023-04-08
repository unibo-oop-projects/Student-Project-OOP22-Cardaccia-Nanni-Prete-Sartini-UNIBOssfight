package app.ui;

import app.game.Game;
import app.util.AppLogger;
import app.util.DataManager;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private static final int LEVEL_CHOICE_LAYOUT_X = 110;
    private static final int LEVEL_CHOICE_LAYOUT_Y = 75;
    private static final int COINS_LAYOUT_Y = 115;
    private static final int LAYOUT_X = 35;
    private static final  int LAYOUT_Y = 160;
    private static final int HELP_LAYOUT_Y = 40;
    private static final int COINS_LABEL_LAYOUTY = 225;
    private static final int ENEMIES_LABEL_LAYOUTY = 195;
    private static final int FINAL_GRADE_LAYOUTY = 270;
    private static final int FINAL_GRADE_LAYOUTX = 235;
    private final Stage mainStage;
    private final AnchorPane mainPane;
    private final List<CustomizedButton> menuButtons;
    private CustomizedSubScene levelChoiceSubScene;
    private CustomizedSubScene scoreSubScene;
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
     * Adds an image to the pane.
     *
     * @param layoutX the x layout of the image
     * @param layoutY the y layout of the image
     * @param url the url of the image of the image
     * @param pane the pane in which the image is added
     */
    public static void addImage(final double layoutX, final double layoutY,
                                final String url, final AnchorPane pane) {
        final ImageView img = new ImageView(url);
        img.setLayoutX(layoutX);
        img.setLayoutY(layoutY);
        img.setOnMouseEntered(event -> img.setEffect(new DropShadow()));
        img.setOnMouseExited(event -> img.setEffect(null));
        pane.getChildren().add(img);
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
            try (InputStream inputStream = ViewManager
                    .class
                    .getClassLoader()
                    .getResourceAsStream(url)) {
                label.setFont(Font.loadFont(inputStream, fontSize));
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
    public static void setFont(final String url, final double fontSize,
                               final Label label) {
        try (InputStream inputStream = ViewManager
                .class
                .getClassLoader()
                .getResourceAsStream(url)) {
            label.setFont(Font.loadFont(inputStream, fontSize));
        } catch (final IOException e) {
            label.setFont(Font.font("Verdana", fontSize));
        }
    }

    private void createButtons() {
        createStartButton();
        createLevelButton();
        createScoresButton();
        createHelpButton();
        createExitButton();
    }

    private void addMenuButton(final CustomizedButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        this.menuButtons.add(button);
        this.mainPane.getChildren().add(button);
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

    private void createLevelButton() {
        final CustomizedButton levelButton = new CustomizedButton("LEVEL");
        addMenuButton(levelButton);
        levelButton.setOnAction(event -> showSubScene(levelChoiceSubScene));
    }

    private void createScoresButton() {
        final CustomizedButton scoreButton = new CustomizedButton("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(event -> showSubScene(scoreSubScene));
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

    private void createSubScenes() {
        this.levelChoiceSubScene = new CustomizedSubScene();
        this.scoreSubScene = new CustomizedSubScene();
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
        try {
            final var score = new DataManager().deserializeScore("score.json");
            final Label grade = new Label(Integer.toString(110 * score.getEarnedPoints()
                                                           / (score.getTotalPoints() * Game.LEVEL_COUNT)));

            this.scoreSubScene.addLabel("\tLEVEL 1: ",
                    LAYOUT_X, HELP_LAYOUT_Y + 10, FONT_SIZE);
            var enemiesDefeated = "Enemies defeated:\t"
                    + score.getLevelStat(1).getKey();
            var coinsCollected = "Coins collected:\t\t"
                    + score.getLevelStat(1).getValue();
            this.scoreSubScene.addLabel(enemiesDefeated, LEVEL_CHOICE_LAYOUT_X,
                    LEVEL_CHOICE_LAYOUT_Y + 10, FONT_SIZE);
            this.scoreSubScene.addLabel(coinsCollected, LEVEL_CHOICE_LAYOUT_X,
                    COINS_LAYOUT_Y, FONT_SIZE);


            this.scoreSubScene.addLabel("\tLEVEL 2: ",
                    LAYOUT_X, LAYOUT_Y, FONT_SIZE);
            enemiesDefeated = "Enemies defeated:\t"
                    + score.getLevelStat(2).getKey();
            coinsCollected = "Coins collected:\t\t"
                    + score.getLevelStat(2).getValue();
            this.scoreSubScene.addLabel(enemiesDefeated, LEVEL_CHOICE_LAYOUT_X,
                    ENEMIES_LABEL_LAYOUTY, FONT_SIZE);
            this.scoreSubScene.addLabel(coinsCollected, LEVEL_CHOICE_LAYOUT_X,
                    COINS_LABEL_LAYOUTY, FONT_SIZE);

            this.scoreSubScene.addLabel("\tFINAL GRADE: ",
                    LAYOUT_X, FINAL_GRADE_LAYOUTY, FONT_SIZE);
            this.scoreSubScene.addLabel(grade.getText(),
                    FINAL_GRADE_LAYOUTX, FINAL_GRADE_LAYOUTY, FONT_SIZE);

        } catch (final IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            throw (IllegalStateException) new IllegalStateException().initCause(e);
        }
    }

    private void setHelpSubScene() {
        final Text text = new Text("""
                Hi, welcome to UNIBOssfight!

                Press 'D' to run forward,
                press 'A' to run backward,
                press 'space' to jump.

                Use the mouse to point and
                shoot the enemies, defeat
                all of them and collect
                all the coins to get
                your degree.

                Good Luck!\s""");

        this.helpSubScene.addLabel(text.getText(),
                LAYOUT_X, HELP_LAYOUT_Y, FONT_SIZE);
    }

    private void showSubScene(final CustomizedSubScene subScene) {
        this.mainPane.getChildren().remove(currentScene);
        subScene.fadeInSubScene();
        this.mainPane.getChildren().add(subScene);
        this.currentScene = subScene;
    }
}
