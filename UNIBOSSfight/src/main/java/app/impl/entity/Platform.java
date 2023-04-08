package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import app.core.entity.AbstractEntity;

/**
 * This class models a platform, which is an obstacle
 * on which the player can jump and run.
 */
public class Platform extends AbstractEntity {

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
     * This method is used to let the entity jump on the platform by
     * checking the collision on the topside of it and updating the
     * ground level.
     *
     * @param collidingEntity the entity colliding
     * @param platform the platform on which the entity is jumping
     */
     public static void jump(final ActiveEntity collidingEntity, final Entity platform) {
         if (collidingEntity.getHitbox().getCollisionSideOnY(platform.getPosition().getY()) > 0
                 && Math.abs(collidingEntity.getHitbox().getIntersectionOnX(platform))
                 > Math.abs(collidingEntity.getHitbox().getIntersectionOnY(platform))) {
             collidingEntity.getBehaviour()
                            .getJumpingBehaviour()
                            .ifPresent(e -> e.accept(collidingEntity, platform));
         }
     }

}
