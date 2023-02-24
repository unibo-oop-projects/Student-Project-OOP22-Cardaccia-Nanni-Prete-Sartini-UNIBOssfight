package impl.entity;

import core.component.Hitbox;
import core.entity.Obstacle;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

public class Flame implements Obstacle {

    private final boolean isHarmful;
    private final int damage;
    private Vector2d flamePosition;
    private Hitbox flameHitbox;

    public Flame() {
        this.isHarmful = true;
        this.damage = 1;
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
        return this.flamePosition;
    }

    @Override
    public Hitbox getHitbox() {
        return this.flameHitbox;
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
