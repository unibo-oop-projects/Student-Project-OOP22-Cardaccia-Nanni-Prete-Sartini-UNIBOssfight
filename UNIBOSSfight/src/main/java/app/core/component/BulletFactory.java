package app.core.component;

import app.core.entity.Bullet;
import javafx.geometry.Point2D;

/**
 * An interface modelling a Factory to create instances of Bullets.
 */
public interface BulletFactory {

    /**
     * This method returns an instance of the player weapon's bullet.
     *
     * @param shootingPosition The starting position of the bullet
     * @param target The target of the bullet
     * @return an instance of the player weapon's bullet.
     */
    Bullet getPlayerBullet(Transform shootingPosition, Point2D target, boolean isPlayerBullet);


    /**
     * This method returns an instance of the bigBullet weapon's bullet.
     *
     * @param shootingPosition The starting position of the bullet
     * @param target The target of the bullet
     * @return an instance of the player weapon's bullet.
     */
    Bullet getBigBullet(Transform shootingPosition, Point2D target, boolean isPlayerBullet);

}
