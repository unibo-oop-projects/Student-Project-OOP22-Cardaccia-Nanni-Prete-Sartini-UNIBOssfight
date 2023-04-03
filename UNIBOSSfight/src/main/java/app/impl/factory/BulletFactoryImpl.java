package app.impl.factory;

import app.core.component.BulletFactory;
import app.core.component.Transform;
import app.core.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.entity.BulletImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BulletFactoryImpl implements BulletFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet getPlayerBullet(final Transform shootingPosition, final Point2D target) {

        return new BulletImpl(shootingPosition,  20, 20,
                new SpriteRenderer(25, 40, Color.BLACK, "magicBullet.png"),
                10, target, 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet getBigBullet(final Transform shootingPosition, final Point2D target) {
        return new BulletImpl(shootingPosition, 40, 40,
                new SpriteRenderer(40, 40, Color.BLACK, "bullet.png"), 200, target, 5);
    }

}
