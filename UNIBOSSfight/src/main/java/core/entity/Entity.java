package core.entity;

import core.component.Hitbox;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

public interface Entity {

    boolean isDisplayed();

    void render(GraphicsContext gc);

    void update();

    Vector2d getPosition();

    Hitbox getHitbox();

}
