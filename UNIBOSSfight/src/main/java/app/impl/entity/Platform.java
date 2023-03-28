package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.Entity;
import app.core.entity.AbstractEntity;
import app.impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class models a platform, which is an obstacle
 * on which the player can jump and run.
 */
public class Platform extends AbstractEntity {

    //platform will eventually move

    /**
     * Creates a new instance of the class Platform.
     *
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
     * Creates a new instance of the class Platform.
     *
     * @param position the position of the platform
     * @param height the height of the platform
     * @param width the width of the platform
     * @param renderer the renderer
     */
    public Platform(final Transform position, final int height,
                    final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

    }

    /**
     * This method is used to let the entity jump on the platform by
     * checking the collision on the topside of it and updating the
     * ground level.
     *
     * @param collidingEntity the entity colliding
     * @param platform the platform on which the entity is jumping
     */
     public static void stop(final Entity collidingEntity, final Entity platform) {
        final double topSide = platform.getHitbox().getTopSide();
        if (collidingEntity.getHitbox().getCollisionSideOnY(platform.getPosition().getY()) < 0
            && Math.abs(collidingEntity.getHitbox().getIntersectionOnX(platform))
                 > Math.abs(collidingEntity.getHitbox().getIntersectionOnY(platform))) {
            collidingEntity.getTransform().setGroundLevel(topSide);
            if (collidingEntity.getTransform().isUnderGroundLevel()) {
                collidingEntity.getTransform().moveOnGroundLevel();
            }
        }
     }

}
