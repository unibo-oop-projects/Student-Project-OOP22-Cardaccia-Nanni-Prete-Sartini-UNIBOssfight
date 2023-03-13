package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class models a wall, which is an obstacle that stops
 * the player while running but can be climbed.
 */
public class Wall extends AbstractEntity {

    /**
     * The constructor of the class which creates a new instance of Wall.
     * @param position the position of the wall
     * @param height the height of the wall
     * @param width the width of the wall
     * @param filename the name of the file containing the sprite for the renderer
     */
    public Wall(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.BROWN, filename));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

    }

}
