package app.core.component;

import app.impl.entity.Bullet;
import javafx.geometry.Point2D;

/**
 * An interface modelling a Factory to create instances of Bullets.
 */
public interface BulletFactory {

    /**
     * This method returns an instance of the player weapon's bullet.
     *
     * @param shootingPosition the starting position of the bullet
     * @param target the target of the bullet
     * @param isPlayerBullet identifier of the Bullet owner
     * @return an instance of the player weapon's bullet.
     */
    Bullet getPlayerBullet(Transform shootingPosition, Point2D target, boolean isPlayerBullet);


    /**
     * This method returns an instance of the bigBullet weapon's bullet.
     *
     * @param shootingPosition the starting position of the bullet
     * @param target the target of the bullet
     * @param isPlayerBullet identifier of the Bullet owner
     * @return an instance of the player weapon's bullet.
     */
    Bullet getBigBullet(Transform shootingPosition, Point2D target, boolean isPlayerBullet);

}
