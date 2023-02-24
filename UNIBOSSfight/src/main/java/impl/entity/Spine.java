package impl.entity;

import core.component.Hitbox;
import core.entity.Obstacle;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

public class Spine implements Obstacle {

    private final boolean isHarmful;
    private final int damage;
    private Vector2d spinePosition;
    private Hitbox spineHitbox;

    public Spine() {
        this.isHarmful = true;
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
    public Vector2d getPosition() {
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
    public boolean isHarmuful() {
        return this.isHarmful;
    }
}
