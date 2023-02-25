package core.entity;

import core.component.Hitbox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface Entity {

    boolean isDisplayed();

    void render(GraphicsContext gc);

    void update();

    Point2D getPosition();

    Hitbox getHitbox();

}
