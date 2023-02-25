package impl.entity;

import core.component.Hitbox;
import core.entity.PassiveEntity;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Spine implements PassiveEntity {

    private Point2D spinePosition;
    private final Hitbox spineHitbox;
    private final boolean isHarmful;
    private final int damage;

    public Spine(final Hitbox hitbox) {
        this.isHarmful = true;
        this.spineHitbox = hitbox;
        this.damage = 2;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public boolean isHarmful() {
        return this.isHarmful;
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
    public void render(GraphicsContext gc) {

    }

    @Override
    public void update() {

    }

}
