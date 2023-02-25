package impl.entity;

import core.component.Hitbox;
import core.entity.PassiveEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;

public class Coin implements PassiveEntity {
    private final boolean isHarmful;
    private final int value;
    private Point2D coinPosition;
    private final Hitbox coinHitbox;

    public Coin(Hitbox hitbox) {
        this.coinHitbox = hitbox;
        this.isHarmful = false;
        this.value = 1;
    }

    @Override
    public boolean isDisplayed() {
        //true if pos.getx entity - pos.getx player < half screen width
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void update() {

    }

    @Override
    public Point2D getPosition() {
        return this.coinPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return this.coinHitbox;
    }

    @Override
    public boolean isHarmful() {
        return this.isHarmful;
    }

    public int getValue() {
        return this.value;
    }
}
