package app.core.component;

import app.core.entity.Entity;
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
     *
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

    /**
     * This method returns the y-axis side in which the collision occurred.
     *
     * @param y the y coordinate of the collided entity
     * @return 1 if it is on the bottom, -1 if it is on the top, 0 otherwise
     */
    double getCollisionSideOnY(double y);

    /**
     * This method returns the y-axis side in which the collision occurred.
     *
     * @param x the y coordinate of the collided entity
     * @return 1 if it is on the bottom, -1 if it is on the top, 0 otherwise
     */
    double getCollisionSideOnX(double x);

    /**
     * This method returns the intersection on the y-axis
     * between the two hitboxes colliding.
     *
     * @param e the collided entity
     * @return the overlapping between the two hitboxes if any
     */
    double getIntersectionOnY(Entity e);

    /**
     * This method returns the intersection on the x-axis
     * between the two hitboxes colliding.
     *
     * @param e the collided entity
     * @return the overlapping between the two hitboxes if any
     */
    double getIntersectionOnX(Entity e);

}
