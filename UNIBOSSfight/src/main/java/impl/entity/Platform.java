package impl.entity;

import core.entity.Brick;
import util.Vector2d;

public class Platform implements Brick {
    private Vector2d position;
    private final boolean isHarmful;

    public Platform() {
        this.isHarmful = false;
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
}
