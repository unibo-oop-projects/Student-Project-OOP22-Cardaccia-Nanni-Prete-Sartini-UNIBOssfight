package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;

/**
 * This class models a harmful obstacle, which is an obstacle that
 * can cause damage to the player if he steps on it.
 */
public class HarmfulObstacle extends AbstractEntity {

    /**
     * Creates a new instance of the class HarmfulObstacle.
     *
     * @param position the position of the flame
     * @param height the height of the flame
     * @param width the width of the flame
     * @param renderer the renderer
     */
    public HarmfulObstacle(final Transform position, final int height,
                           final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }
}
