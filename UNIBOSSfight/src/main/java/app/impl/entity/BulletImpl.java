package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.Bullet;
import javafx.geometry.Point2D;

/**
 * This class implements the bullet.
 */
public class BulletImpl extends Bullet {

    /**
     * Creates a new instance of the class Bullet.
     *
     * @param startingPos the starting position of the bullet
     * @param height the height of the bullet
     * @param width the width of the bullet
     * @param renderer the renderer of the bullet
     * @param damage the damage caused by bullet
     * @param target the spot giving the trajectory
     * @param speed the speed of the bullet
     */
    public BulletImpl(final Transform startingPos, final int height, final int width,
                      final Renderer renderer, final int damage, final Point2D target,
                      final int speed, final boolean isPlayerBullet) {
        super(startingPos, height, width, renderer, damage, target, speed, isPlayerBullet);
    }

}
