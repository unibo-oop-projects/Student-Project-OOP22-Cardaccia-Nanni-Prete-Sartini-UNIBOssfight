package impl.entity;

import core.component.Component;
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
        //TO DO
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        //TO DO
    }

    @Override
    public void update() {
        //TO DO
    }

    @Override
    public Vector2d getPosition() {
        return null;
    }
}
