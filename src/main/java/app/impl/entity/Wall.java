package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.ActiveEntity;
import app.core.entity.Entity;

/**
 * This class models a wall, which is an obstacle that stops
 * the player while running but can be climbed.
 */
public class Wall extends AbstractEntity {

    /**
     * Creates a new instance of the class Wall.
     *
     * @param position the position of the wall
     * @param height the height of the wall
     * @param width the width of the wall
     * @param renderer the renderer
     */
    public Wall(final Transform position, final int height,
                final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }

    /**
     * This static method is used to stop the entity colliding with the wall,
     * by checking on each side of it if there has been an intersection
     * between the hitboxes.
     *
     * @param collidingEntity the entity colliding
     * @param wall the wall stopping the entity
     */
    public static void stop(final ActiveEntity collidingEntity,
                               final Entity wall) {
        if (Math.abs(collidingEntity.getHitbox().getIntersectionOnX(wall))
                > Math.abs(collidingEntity.getHitbox().getIntersectionOnY(wall))) {
            if (collidingEntity.getHitbox()
                    .getCollisionSideOnY(wall.getPosition().getY()) < 0) {
                collidingEntity.getBehaviour()
                               .getBottomStoppingBehaviour()
                               .ifPresent(e -> e.accept(collidingEntity, wall));
            } else {
                collidingEntity.getBehaviour()
                               .getJumpingBehaviour()
                               .ifPresent(e -> e.accept(collidingEntity, wall));
            }
        } else {
            collidingEntity.getBehaviour()
                           .getSideStoppingBehaviour()
                           .ifPresent(e -> e.accept(collidingEntity, wall));
        }
    }
}
