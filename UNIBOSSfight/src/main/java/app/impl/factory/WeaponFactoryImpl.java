package app.impl.factory;

import app.core.component.BulletFactory;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.component.WeaponFactory;
import app.core.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Implementation of the WeaponFactory interface.
 */
public class WeaponFactoryImpl implements WeaponFactory {

    private final BulletFactory bulletFactory = new BulletFactoryImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Weapon getPlayerWeapon(final Transform playerPos) {

        return new WeaponImpl(playerPos, new SpriteRenderer(50, 170, Color.RED, "gun.png"), 55) {

            @Override
            public Bullet fire(final Point2D target) {
                return bulletFactory.getPlayerBullet(this.getShootingPosition(), target);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weapon getBigBulletGun(final Transform userPos) {
        return new WeaponImpl(userPos, new SpriteRenderer(300, 700, Color.RED, "gun.png"), 125) {

            @Override
            public Bullet fire(final Point2D target) {
                return bulletFactory.getBigBullet(this.getShootingPosition(), target);
            }

        };
    }
}
