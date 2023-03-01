package core.entity;

import core.component.Hitbox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public interface Entity {

    public enum Inputs {
        LEFT,
        RIGHT,
        SPACE,
        EMPTY
    }

    boolean isDisplayed(Point2D position, double width);

    void render(GraphicsContext gc, Point2D position);

    ImageView render(Point2D position);

    void update(Inputs input);

    Point2D getPosition();

    Hitbox getHitbox();

}
