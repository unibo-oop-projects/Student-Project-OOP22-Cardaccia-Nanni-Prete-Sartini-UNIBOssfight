package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Platform extends AbstractEntity {
    private final boolean isHarmful;

    //platform will eventually move
    private final boolean isMoving;

    public Platform(final Transform position, final int height,
                    final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.GREEN, filename));
        this.isHarmful = false;
        this.isMoving = false;
    }

    public boolean isHarmful() {
        return this.isHarmful;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update(Inputs input) {

    }

}
