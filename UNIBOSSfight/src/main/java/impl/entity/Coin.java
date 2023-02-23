package impl.entity;

import core.entity.PassiveEntity;
import util.Vector2d;

public class Coin implements PassiveEntity {

    private final boolean isHarmful;
    private final int value;

    private Vector2d position;

    public Coin() {
        this.isHarmful = false;
        this.value = 1;
    }
    @Override
    public void update() {

    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isHarmuful() {
        return this.isHarmful;
    }

    public int getValue() {
        return this.value;
    }
}
