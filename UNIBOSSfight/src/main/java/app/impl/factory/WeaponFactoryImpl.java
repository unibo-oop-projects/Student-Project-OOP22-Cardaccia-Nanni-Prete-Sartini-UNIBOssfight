package app.impl.factory;

import app.core.component.BulletFactory;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.component.WeaponFactory;
import app.core.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.component.TransformImpl;
import app.impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import app.util.Window;

import java.awt.*;

/**
 * Implementation of the WeaponFactory interface.
 */
public class WeaponFactoryImpl implements WeaponFactory {

    private final BulletFactory bulletFactory = new BulletFactoryImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public WeaponImpl getPlayerWeapon(final Transform playerPos, final boolean isPlayerWeapon) {

        return new WeaponImpl(playerPos, new SpriteRenderer(50, 170, Color.RED, "gun.png"), 55) {

            @Override
            public Bullet fire(final Point2D target) {
                return bulletFactory.getPlayerBullet(this.getShootingPosition(), target, isPlayerWeapon);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeaponImpl getBigBulletGun(final Transform userPos, final boolean isPlayerWeapon) {
        return new WeaponImpl(userPos, new SpriteRenderer(300, 700, Color.RED, "gun.png"), 125) {

            @Override
            public Bullet fire(final Point2D target) {
                return bulletFactory.getBigBullet(getShootingPosition(), target ,false);
            }

        };
    }

    @Override
    public WeaponImpl getGhiniGun(Transform userPos, boolean isPlayerWeapon) {
        return new WeaponImpl(userPos, new SpriteRenderer(200, 500, Color.RED, "gun.png"),125){

            @Override
            public Bullet fire(Point2D target) {
                return bulletFactory.getBigBullet(new TransformImpl(new Point2D(target.getX(), Window.getHeight()), 0),
                        target, false);
            }

        };
    }
}
