package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.entity.Enemy;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class EnemyImpl extends Enemy {

    public EnemyImpl(int health, Transform position) {
        super(health, position);
    }

    public int getDamage() {
        return 0;
    }

    @Override
    public void setHealth(int health) {

    }

    public int getHealth() {
        return 0;
    }

    public void attack() {

    }

    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isDisplayed(Point2D position) {
        return false;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update() {

    }

    @Override
    public Point2D getPosition() {
        return null;
    }

    @Override
    public Hitbox getHitbox() {
        return null;//TODO
    }
}
