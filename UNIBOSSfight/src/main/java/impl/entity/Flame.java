package impl.entity;

import core.component.Hitbox;
import core.entity.Obstacle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Flame implements Obstacle {

    private final boolean isHarmful;
    private final int damage;
    private Point2D flamePosition;
    private final Hitbox flameHitbox;

    public Flame(Hitbox hitbox) {
        this.flameHitbox = hitbox;
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
    public Point2D getPosition() {
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
    public boolean isHarmful() {
        return this.isHarmful;
    }
}
