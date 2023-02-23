package impl.entity;

import core.component.Component;
import core.entity.Obstacle;

public class Spine implements Obstacle {

    private final boolean isHarmful;
    private final int damage;

    public Spine() {
        this.isHarmful = true;
        this.damage = 2;
    }
    @Override
    public void update() {

    }

    @Override
    public Component getPosition() {
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
