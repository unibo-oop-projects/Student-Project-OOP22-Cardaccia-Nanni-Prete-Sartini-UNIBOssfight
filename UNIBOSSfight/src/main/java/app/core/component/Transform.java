package app.core.component;

import javafx.geometry.Point2D;

/**
 * This class models the component representing the concept
 * of the position and the rotation of an entity.
 */
public interface Transform extends Component {

    /**
     * Translates the current position with the vector of components x and y.
     *
     * @param x position on x-axis
     * @param y position on y-axis
     */
    void move(double x, double y);

    /**
     * Takes the entity back on the ground level if it is under it.
     */
    void moveOnGroundLevel();

    /**
     * This method returns the current ground level.
     *
     * @return the current ground level
     */
    double getGroundLevel();

    /**
     * Changes the ground level.
     *
     * @param yGround the new value of the ground
     */
    void setGroundLevel(double yGround);

    /**
     * This method returns true if the entity is under the ground
     * level, false otherwise.
     *
     * @return true is the entity is under the ground level
     */
    boolean isUnderGroundLevel();

    /**
     * Sets the ground level to the default one, which is the height of the window.
     */
    void resetGroundLevel();

    /**
     * This method returns a copy of the current position of the entity.
     *
     * @return a copy of the current position of the entity
     */
    Point2D getPosition();

    /**
     * Moves the entity to a new position.
     *
     * @param x new position on x-axis
     * @param y new position on y-axis
     */
    void moveTo(double x, double y);

    /**
     * Returns the rotation.
     *
     * @return the rotation
     */
    double getRotation();

    /**
     * Sets the rotation.
     * @param rotation the value of the rotation
     */
    void setRotation(double rotation);

    /**
     * Creates a copy of the Transform given as input.
     *
     * @return a Transform which is the exact copy of the passed one
     */
    Transform copyOf();
}
