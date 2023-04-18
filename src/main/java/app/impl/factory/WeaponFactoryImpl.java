package app.impl.factory;

import app.core.component.BulletFactory;
import app.core.component.Transform;
import app.core.component.WeaponFactory;
import app.impl.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.component.TransformImpl;
import app.impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import app.util.Window;

/**
 * Implementation of the WeaponFactory interface.
 */
public class WeaponFactoryImpl implements WeaponFactory {

    private final BulletFactory bulletFactory = new BulletFactoryImpl();
    private static final int PLAYER_WEAPON_HEIGHT = 50;
    private static final int PLAYER_WEAPON_WIDTH = 170;
    private static final int PLAYER_WEAPON_OFFSET = 55;
    private static final int BIG_WEAPON_HEIGHT = 150;
    private static final int BIG_WEAPON_WIDTH = 500;
    private static final int BIG_WEAPON_OFFSET = 125;
    private static final int METEOR_WEAPON_HEIGHT = 150;
    private static final int METEOR_WEAPON_WIDTH = 500;
    private static final int METEOR_WEAPON_OFFSET = 125;

    /**
     * {@inheritDoc}
     */
    @Override
    public WeaponImpl getPlayerWeapon(final Transform playerPos, final boolean isPlayerWeapon) {

        return new WeaponImpl(playerPos, new SpriteRenderer(PLAYER_WEAPON_HEIGHT, PLAYER_WEAPON_WIDTH,
                Color.RED, "gun.png"), PLAYER_WEAPON_OFFSET) {

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
        return new WeaponImpl(userPos, new SpriteRenderer(BIG_WEAPON_HEIGHT, BIG_WEAPON_WIDTH,
                Color.RED, "gun.png"), BIG_WEAPON_OFFSET) {

            @Override
            public Bullet fire(final Point2D target) {
                return bulletFactory.getBigBullet(getShootingPosition(), target, false);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeaponImpl getMeteorGun(final Transform userPos, final boolean isPlayerWeapon) {
        return new WeaponImpl(userPos, new SpriteRenderer(METEOR_WEAPON_HEIGHT, METEOR_WEAPON_WIDTH,
                Color.RED, "gun.png"), METEOR_WEAPON_OFFSET) {

            @Override
            public Bullet fire(final Point2D target) {
                return bulletFactory.getBigBullet(new TransformImpl(new Point2D(target.getX(), Window.getHeight()), 0),
                        target, false);
            }

        };
    }
}
