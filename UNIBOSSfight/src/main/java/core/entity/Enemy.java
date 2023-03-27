package core.entity;

import core.component.Renderer;
import core.component.Transform;
import impl.component.TransformImpl;

/**
 * This class models the enemy of the game, which causes damage
 * to the player and can be killed by him.
 */
public abstract class Enemy extends AbstractEntity {

    /**
     * The constructor of the class.
     *
     * @param position the position of the enemy
     * @param height the height of the enemy
     * @param width the width of the enemy
     * @param renderer the renderer of the enemy
     */
    public Enemy(final Transform position, final int height,
                 final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }

}
