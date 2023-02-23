package impl.entity;

import core.component.Component;
import core.entity.Obstacle;

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
