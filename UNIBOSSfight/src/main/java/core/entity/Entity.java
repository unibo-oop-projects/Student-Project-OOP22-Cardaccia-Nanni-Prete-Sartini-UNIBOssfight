package core.entity;

import core.component.Hitbox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface Entity {

    boolean isDisplayed(Point2D position);

    void render(GraphicsContext gc, Point2D position);

    void update();

    Point2D getPosition();

    Hitbox getHitbox();

}
