package impl.entity;

import core.component.Hitbox;
import core.entity.PassiveEntity;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Platform implements PassiveEntity {
    private Point2D position;
    private final Hitbox platformHitbox;
    private final boolean isHarmful;

    //platform will eventually move
    private final boolean isMoving;

    public Platform(final Hitbox hitbox) {
        this.isHarmful = false;
        this.isMoving = false;
        this.platformHitbox = hitbox;
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
        return this.position;
    }

    @Override
    public Hitbox getHitbox() {
        return this.platformHitbox;
    }


    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update() {

    }

}
