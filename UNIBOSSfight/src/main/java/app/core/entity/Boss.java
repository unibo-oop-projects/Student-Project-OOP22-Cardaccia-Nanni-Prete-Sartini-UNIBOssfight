package app.core.entity;

import app.core.component.Transform;
import app.core.component.Weapon;
import app.impl.component.AnimationSpriteRenderer;
import app.impl.component.WeaponImpl;
import app.impl.entity.Bullet;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * This class models a more specific type of enemy, the boss,
 * that can cause damage by using a weapon.
 */
public abstract class Boss extends Enemy {

    /**
     * Creates a new instance of the class Boss.
     *
     * @param position the position of the boss
     * @param height the height of the boss
     * @param width the width of the boss
     * @param filename the name of the file containing
     *                 the sprite of the boss
     */
    public Boss(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width, new AnimationSpriteRenderer(height, width, Color.RED, filename));
    }

    /**
     * This method returns the weapon of the boss.
     *
     * @return the weapon of the boss
     */
    public abstract Weapon getWeapon();

    /**
     * Method that makes the Boss shoot.
     *
     * @param target target to be shot
     * @return the bullet to be rendered
     */
    public abstract Bullet shoot(Point2D target);

    /**
     * Method to set the Bosses Weapon.
     *
     * @param weapon the new Weapon
     */
    public abstract void setWeapon(WeaponImpl weapon);

    /**
     * Returns the rate of fire of the boss.
     *
     * @return the number of frames between each shot
     */
    public abstract int getRateOfFire();

    /**
     * Setter for the rateOfFire parameter.
     *
     * @param rateOfFire frames to wait between shots
     */
    public abstract void setRateOfFire(int rateOfFire);
}
