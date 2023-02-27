package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Flame extends AbstractEntity {
    private final boolean isHarmful;
    private final int damage;

    public Flame(final Transform position, final int height,
                 final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.ORANGE, filename));
        this.isHarmful = true;
        this.damage = 1;
    }

    public boolean isHarmful() {
        return this.isHarmful;
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update(Inputs input) {

    }

}
