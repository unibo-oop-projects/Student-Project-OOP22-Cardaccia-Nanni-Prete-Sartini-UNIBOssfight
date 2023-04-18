package app.impl.factory;

import app.core.component.BulletFactory;
import app.core.component.Transform;
import app.impl.entity.Bullet;
import app.impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Implementation of the BulletFactory interface.
 */
public class BulletFactoryImpl implements BulletFactory {

    private static final int PLAYER_BULLET_HEIGHT = 25;
    private static final int PLAYER_BULLET_WIDTH = 40;
    private static final int PLAYER_BULLET_SPEED = 30;
    private static final int PLAYER_BULLET_DAMAGE = 10;
    private static final int BIG_BULLET_HEIGHT = 40;
    private static final int BIG_BULLET_WIDTH = 60;
    private static final int BIG_BULLET_SPEED = 4;
    private static final int BIG_BULLET_DAMAGE = 25;

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet getPlayerBullet(final Transform shootingPosition, final Point2D target, final boolean isPlayerBullet) {

        return new Bullet(shootingPosition,  PLAYER_BULLET_HEIGHT, PLAYER_BULLET_WIDTH,
                new SpriteRenderer(PLAYER_BULLET_HEIGHT, PLAYER_BULLET_WIDTH, Color.BLACK, "magicBullet.png"),
                PLAYER_BULLET_DAMAGE, target, PLAYER_BULLET_SPEED, isPlayerBullet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet getBigBullet(final Transform shootingPosition, final Point2D target, final  boolean isPlayerBullet) {
        return new Bullet(shootingPosition, BIG_BULLET_HEIGHT, BIG_BULLET_WIDTH,
                new SpriteRenderer(BIG_BULLET_HEIGHT, BIG_BULLET_WIDTH,
                        Color.BLACK, "magicBullet.png"), BIG_BULLET_DAMAGE,
                        target, BIG_BULLET_SPEED, isPlayerBullet);
    }

}
