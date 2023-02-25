package impl.entity;

import core.component.Hitbox;
import core.entity.PassiveEntity;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Wall implements PassiveEntity {

    private Point2D wallPosition;
    private final Hitbox wallHitbox;
    private final boolean isHarmful;

    public Wall(final Hitbox hitbox) {
        this.isHarmful = false;
        this.wallHitbox = hitbox;
    }

    @Override
    public boolean isHarmful() {
        return this.isHarmful;
    }
    @Override
    public boolean isDisplayed(Point2D position) {
        return false;
    }

    @Override
    public Point2D getPosition() {
        return this.wallPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return this.wallHitbox;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update() {

    }

}
