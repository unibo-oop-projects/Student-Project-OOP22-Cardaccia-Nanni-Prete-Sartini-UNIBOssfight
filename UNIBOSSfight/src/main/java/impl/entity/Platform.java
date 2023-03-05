package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

public class Platform extends AbstractEntity {

    //platform will eventually move
    private final boolean isMoving;

    public Platform(final Transform position, final int height,
                    final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.GREEN, filename));
        this.isMoving = false;
    }

    @Override
    public void update(final Inputs input) {

    }

}
