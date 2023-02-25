package impl.entity;

import core.component.Hitbox;
import core.entity.PassiveEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;

public abstract class Coin implements PassiveEntity {
    private Point2D coinPosition;
    private final Hitbox coinHitbox;
    private final boolean isHarmful;
    private final int value;

    public Coin(final Hitbox hitbox) {
        this.isHarmful = false;
        this.coinHitbox = hitbox;
        this.value = 1;
    }

    @Override
    public boolean isDisplayed() {

        return false;
    }

    @Override
    public boolean isHarmful() {
        return this.isHarmful;
    }

    @Override
    public Point2D getPosition() {
        return this.coinPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return this.coinHitbox;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void update() {

    }

}
