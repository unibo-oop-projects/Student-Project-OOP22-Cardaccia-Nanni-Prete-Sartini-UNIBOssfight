package app.core.component;

import app.core.entity.Bullet;
import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * This class models the weapon component, which is used by an entity
 * to inflict damage towards other entities by firing bullets.
 */
public interface Weapon {

    /**
     * Renders the weapon.
     *
     * @param direction the direction of the weapon
     * @param rotation the rotation of the weapon
     * @return the Node that will be given as input to the Scene,
     *         representing the rendered weapon
     */
    Node render(int direction, int rotation);

    /**
     * Used to create new bullets shot by the weapon towards a certain target.
     *
     * @param target the target at which the weapon is pointing
     * @return the bullet fired
     */
    Bullet fire(Point2D target);

    /**
     * Used to return position from which to shoot the bullets.
     *
     * @return the position from which the bullet is shot
     */
    Transform getShootingPos();

    /**
     * Method used to modify the direction on the Y-axis of the weapon.
     *
     * @param direction new direction on Y-axis
     */
    void setYDirection(int direction);

    /**
     * Updates the position of the weapon with the given one.
     *
     * @param newPos the new position of the weapon
     */
    void updatePosition(Transform newPos);

    /**
     * Returns the position of the weapon.
     *
     * @return the position of the weapon
     */
    Transform getWeaponPosition();

    Transform getUserPosition();

    Transform getShootingPosition();
}
