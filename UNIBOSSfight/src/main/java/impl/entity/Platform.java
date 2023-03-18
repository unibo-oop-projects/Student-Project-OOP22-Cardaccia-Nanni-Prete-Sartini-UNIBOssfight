package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class models a platform, which is an obstacle
 * on which the player can jump and run.
 */
public class Platform extends AbstractEntity {

    //platform will eventually move

    /**
     * Creates a new instance of the class Platform.
     * @param position the position of the platform
     * @param height the height of the platform
     * @param width the width of the platform
     * @param filename the name of the file containing the sprite for the renderer
     */
    public Platform(final Transform position, final int height,
                    final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.GREEN, filename));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

    }

}
