package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import app.impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

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
     * @param filename the name of the file containing the sprite for the renderer
     */
    public Wall(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.BROWN, filename));
    }

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
    public static void stop(final Entity collidingEntity,
                               final Entity wall) {
        if (Math.abs(collidingEntity.getHitbox().getIntersectionOnX(wall))
                > Math.abs(collidingEntity.getHitbox().getIntersectionOnY(wall))) {
            if (collidingEntity.getHitbox()
                    .getCollisionSideOnY(wall.getPosition().getY()) < 0) {
                collidingEntity.getTransform().moveTo(collidingEntity.getPosition().getX(),
                        wall.getPosition().getY() - collidingEntity.getHeight() - 1);
            } else {
                final double topSide = wall.getHitbox().getTopSide();
                collidingEntity.getTransform().setGroundLevel(topSide);
                if (collidingEntity.getTransform().isUnderGroundLevel()) {
                    collidingEntity.getTransform().moveOnGroundLevel();
                }
            }
        } else {
            collidingEntity.getTransform()
                    .move(collidingEntity.getHitbox().getIntersectionOnX(wall), 0);
        }
    }

}
