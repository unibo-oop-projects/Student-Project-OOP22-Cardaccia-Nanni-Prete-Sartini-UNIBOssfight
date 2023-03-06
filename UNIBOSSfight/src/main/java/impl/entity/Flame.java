package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class models a flame, which is an obstacle that
 * can cause damage to the player if he steps on it.
 */
public class Flame extends AbstractEntity {

    /**
     * @param position the position of the flame
     * @param height the height of the flame
     * @param width the width of the flame
     * @param filename the name of the file containing the sprite for the renderer
     */
    public Flame(final Transform position, final int height,
                 final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.ORANGE, filename));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

    }

}
