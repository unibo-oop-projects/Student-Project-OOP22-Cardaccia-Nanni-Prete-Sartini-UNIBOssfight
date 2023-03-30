package app.impl.factory;

import app.core.component.BulletFactory;
import app.core.component.Transform;
import app.core.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.entity.BulletImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BulletFactoryImpl implements BulletFactory {

    @Override
    public Bullet getPlayerBullet(final Transform playerPos, final Point2D target) {

        return new BulletImpl(playerPos,  20, 20,
                new SpriteRenderer(20, 20, Color.BLACK, "bullet.png"),
                100, target, 20);
    }

    @Override
    public Bullet getBigBullet(final Transform startingPos, final Point2D target) {
        return new BulletImpl(startingPos, 40, 40,
                new SpriteRenderer(40, 40, Color.BLACK, "bullet.png"), 200, target, 5);
    }

}
