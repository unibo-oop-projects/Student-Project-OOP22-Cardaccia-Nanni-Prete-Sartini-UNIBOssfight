package impl.entity;

import core.component.Component;
import core.entity.Enemy;

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
    public void update() {

    }

    @Override
    public Component getPosition() {
        return null;
    }
}
