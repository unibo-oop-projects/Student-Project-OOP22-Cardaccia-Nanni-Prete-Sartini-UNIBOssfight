package impl.entity;

import core.component.Component;
import core.component.Hitbox;
import core.entity.Enemy;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

public class EnemyImpl implements Enemy {

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void setHealth(int health) {

    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void attack() {

    }

    @Override
    public boolean isDead() {
        return false;
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
        return null;
    }

    @Override
    public Hitbox getHitbox() {
        return null;//TODO
    }
}
