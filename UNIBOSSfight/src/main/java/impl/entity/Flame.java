package impl.entity;

import core.component.Component;
import core.entity.Obstacle;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

public class Flame implements Obstacle {

    private final boolean isHarmful;
    private final int damage;

    public Flame() {
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
