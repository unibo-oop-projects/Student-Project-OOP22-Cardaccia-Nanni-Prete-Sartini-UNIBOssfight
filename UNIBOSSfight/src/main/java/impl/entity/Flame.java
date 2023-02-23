package impl.entity;

import core.entity.Obstacle;
import util.Vector2d;

public class Flame implements Obstacle {

    private final boolean isHarmful;
    private final int damage;

    public Flame() {
        this.isHarmful = true;
        this.damage = 1;
    }
    @Override
    public void update() {

    }

    @Override
    public Vector2d getPosition() {
        return null;
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
