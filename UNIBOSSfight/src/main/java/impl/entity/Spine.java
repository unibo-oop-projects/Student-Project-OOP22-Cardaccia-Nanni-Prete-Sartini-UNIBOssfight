package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class models a spine, which is an obstacle that
 * can cause damage to the player if he steps on it.
 */
public class Spine extends AbstractEntity {

    /**
     * The constructor of the class which creates a new instance of Spine.
     * @param position the position of the spine
     * @param height the height of the spine
     * @param width the width of the spine
     * @param filename the name of the file containing the sprite for the renderer
     */
    public Spine(final Transform position, final int height,
                 final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.GRAY, filename));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

    }

}
