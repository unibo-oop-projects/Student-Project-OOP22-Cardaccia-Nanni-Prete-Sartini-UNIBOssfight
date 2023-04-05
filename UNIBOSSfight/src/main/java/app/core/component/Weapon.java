package app.core.component;

import app.impl.entity.Bullet;
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
     * @param playerPosition the position of the player
     * @return the Node that will be given as input to the Scene,
     *         representing the rendered weapon
     */
    Node render(Point2D playerPosition, int direction, int rotation);

    /**
     * Used to create new bullets shot by the weapon towards a certain target.
     *
     * @param target the target at which the weapon is pointing
     * @return the bullet fired
     */
    Bullet fire(Point2D target);

    /**
     * Method used to modify the direction on the Y-axis of the weapon.
     *
     * @param direction new direction on Y-axis
     */
    void setYDirection(int direction);

    void setXDirection(int xDirection);

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

    /**
     * Returns the rendering position of the weapon.
     *
     * @return the position in which to render the weapon
     */
    Transform getRenderPosition();

    /**
     * Returns the position of the weapon user.
     *
     * @return the position in which to render the weapon
     */
    Transform getUserPosition();

    /**
     * Used to return position from which to shoot the bullets.
     *
     * @return the position from which the bullet is shot
     */
    Transform getShootingPosition();

    double updateRotation(Point2D target);
}
