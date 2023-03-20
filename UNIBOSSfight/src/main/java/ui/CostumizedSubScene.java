package ui;

import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CostumizedSubScene extends SubScene {

    private final static String FONT_PATH = "kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "yellow_panel.png";

    public CostumizedSubScene() {
        super(new AnchorPane(), 600, 400);
        prefHeight(400);
        prefWidth(600);

    BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,
            600, 400,false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, null);
    AnchorPane root2 = (AnchorPane) this.getRoot();
    root2.setBackground(new Background(image));
    }
}
