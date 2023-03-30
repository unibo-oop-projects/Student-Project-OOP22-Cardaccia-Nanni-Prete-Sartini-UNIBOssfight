package app.core.component;

import app.core.entity.Bullet;
import javafx.geometry.Point2D;

public interface BulletFactory {

    Bullet getPlayerBullet(final Transform playerPos, final Point2D target);

    Bullet getBigBullet(final Transform startingPos, final Point2D target);

}
