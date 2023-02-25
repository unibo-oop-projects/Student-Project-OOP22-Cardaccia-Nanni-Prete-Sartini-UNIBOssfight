package impl.entity;

import core.component.Hitbox;
import core.entity.Brick;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Platform implements Brick {
    private Point2D position;
    private Hitbox platformHitbox;
    private final boolean isHarmful;

    public Platform() {
        this.isHarmful = false;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void update() {

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
    public boolean isHarmuful() {
        return this.isHarmful;
    }
}
