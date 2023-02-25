package impl.entity;

import core.component.Hitbox;
import core.entity.Obstacle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Spine implements Obstacle {

    private final boolean isHarmful;
    private final int damage;
    private Point2D spinePosition;
    private final Hitbox spineHitbox;

    public Spine(Hitbox hitbox) {
        this.isHarmful = true;
        this.spineHitbox = hitbox;
        this.damage = 2;
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
        return this.spinePosition;
    }

    @Override
    public Hitbox getHitbox() {
        return this.spineHitbox;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean isHarmful() {
        return this.isHarmful;
    }
}
