package core.component;

import javafx.geometry.Point2D;
import util.Window;

/**
 * This class models the component representing the concept
 * of the position and the rotation of an entity.
 */
public class Transform implements Component {

    private Point2D position;
    private final float rotation;
    private double yGround = Window.getHeight();

    /**
     * Creates a new instance of the class Transform.
     * @param position the starting position of the entity
     * @param rotation the rotation of the entity
     */
    public Transform(final Point2D position, final float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Translates the current position with the vector of components x and y.
     * @param x position on x-axis
     * @param y position on y-axis
     */
    public void move(final double x, final double y) {
        this.position = this.position.add(x, y);
    }

    /**
     * Takes the entity back on the ground level if it is under it.
     */
    public void moveOnGroundLevel() {
        if (this.isUnderGroundLevel()) {
            this.position = new Point2D(this.position.getX(), yGround);
        }
    }

    /**
     * @return the current ground level
     */
    public double getGroundLevel() {
        return this.yGround;
    }

    /**
     * Changes the ground level.
     * @param yGround the new value of the ground
     */
    public void setGroundLevel(final double yGround) {
        this.yGround = yGround;
    }

    /**
     * @return true is the entity is under the ground level
     */
    public boolean isUnderGroundLevel() {
        return this.position.getY() > yGround;
    }

    /**
     * Sets the ground level to the default one, which is the height of the window.
     */
    public void resetGroundLevel() {
        setGroundLevel(Window.getHeight());
        moveOnGroundLevel();
    }

    /**
     * @return a copy of the current position of the entity
     */
    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    /**
     * Creates a copy of the Transform given as input.
     * @param input the Transform to copy
     * @return a Transform which is the exact copy of the passed one
     */
    public static Transform copyOf(final Transform input) {
        return new Transform(
                new Point2D(input.getPosition().getX(), input.getPosition().getY()),
                input.rotation
        );
    }

    /**
     * Moves the entity to a new position.
     * @param x new position on x-axis
     * @param y new position on y-axis
     */
    public void moveTo(final double x, final double y) {
        this.position = new Point2D(x, y);
    }
}
