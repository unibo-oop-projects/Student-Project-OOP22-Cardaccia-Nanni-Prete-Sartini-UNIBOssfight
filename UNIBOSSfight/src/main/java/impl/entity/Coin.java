package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Coin extends AbstractEntity {
    private final int value;

    public Coin(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.YELLOW, filename));
        this.value = 1;
    }

    @Override
    public boolean isDisplayed(Point2D position) {
        return false;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update(Inputs input) {

    }

}
