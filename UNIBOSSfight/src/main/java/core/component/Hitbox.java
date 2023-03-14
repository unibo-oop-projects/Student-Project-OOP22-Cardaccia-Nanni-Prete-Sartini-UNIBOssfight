package core.component;

import javafx.geometry.Point2D;

/**
 * This class models the hitbox component, which represents the
 * rectangular area occupied by the entity and is used to detect
 * the collisions between entities.
 */
public interface Hitbox extends Component {

    /**
     * Checks if there has been a collision between two entities
     * by controlling if the two hitboxes are overlapping.
     *
     * @param target the hitbox of the entity on which the collision
     *               is being checked
     * @return true if the collision occurred, false otherwise
     */
    boolean collide(Hitbox target);

    /**
     * Updates the hitbox by giving it a new position.
     * @param newPos new position of the hitbox
     */
    void update(Point2D newPos);

    /**
     * This method returns the left side of the hitbox.
     *
     * @return the left side of the hitbox
     */
    double getLeftSide();

    /**
     * This method returns the right side of the hitbox.
     *
     * @return the right side of the hitbox
     */
    double getRightSide();

    /**
     * This method returns the top side of the hitbox.
     *
     * @return the top side of the hitbox
     */
    double getTopSide();

    /**
     * This method returns the bottom side of the hitbox.
     *
     * @return the bottom side of the hitbox
     */
    double getBottomSide();

}
