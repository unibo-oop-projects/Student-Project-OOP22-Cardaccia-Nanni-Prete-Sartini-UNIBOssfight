package impl.component;

import core.component.Transform;
import javafx.geometry.Point2D;
import util.Window;

/**
 * This class models the component representing the concept
 * of the position and the rotation of an entity.
 */
public class TransformImpl implements Transform {

    private Point2D position;
    private final float rotation;
    private transient double yGround = Window.getHeight();

    /**
     * Creates a new instance of the class Transform.
     *
     * @param position the starting position of the entity
     * @param rotation the rotation of the entity
     */
    public TransformImpl(final Point2D position, final float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Translates the current position with the vector of components x and y.
     *
     * @param x position on x-axis
     * @param y position on y-axis
     */
    @Override
    public void move(final double x, final double y) {
        this.position = this.position.add(x, y);
    }

    /**
     * Takes the entity back on the ground level if it is under it.
     */
    @Override
    public void moveOnGroundLevel() {
        if (this.isUnderGroundLevel()) {
            this.position = new Point2D(this.position.getX(), yGround);
        }
    }

    /**
     * This method returns the current ground level.
     *
     * @return the current ground level
     */
    @Override
    public double getGroundLevel() {
        return this.yGround;
    }

    /**
     * Changes the ground level.
     *
     * @param yGround the new value of the ground
     */
    @Override
    public void setGroundLevel(final double yGround) {
        this.yGround = yGround;
    }

    /**
     * This method returns true if the entity is under the ground
     * level, false otherwise.
     *
     * @return true is the entity is under the ground level
     */
    @Override
    public boolean isUnderGroundLevel() {
        return this.position.getY() > yGround;
    }

    /**
     * Sets the ground level to the default one, which is the height of the window.
     */
    @Override
    public void resetGroundLevel() {
        setGroundLevel(Window.getHeight());
        moveOnGroundLevel();
    }

    /**
     * This method returns a copy of the current position of the entity.
     *
     * @return a copy of the current position of the entity
     */
    @Override
    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    /**
     * Moves the entity to a new position.
     *
     * @param x new position on x-axis
     * @param y new position on y-axis
     */
    @Override
    public void moveTo(final double x, final double y) {
        this.position = new Point2D(x, y);
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    /**
     * Creates a copy of the Transform given as input.
     *
     * @param input the Transform to copy
     * @return a Transform which is the exact copy of the passed one
     */
    public static Transform copyOf(Transform input) {
        return new TransformImpl(
                new Point2D(input.getPosition().getX(), input.getPosition().getY()),
                input.getRotation()
        );
    }
}
