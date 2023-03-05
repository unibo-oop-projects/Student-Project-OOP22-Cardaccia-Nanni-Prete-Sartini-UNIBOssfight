package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

public class Flame extends AbstractEntity {

    public Flame(final Transform position, final int height,
                 final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.ORANGE, filename));
    }

    @Override
    public void update(final Inputs input) {

    }

}
