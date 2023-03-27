package impl.component;

import core.component.Transform;
import javafx.geometry.Point2D;
import util.Window;

/**
 * This class implements the Transform.
 */
public class TransformImpl implements Transform {

    private Point2D position;
    private double rotation;
    private transient double yGround = Window.getHeight();

    /**
     * Creates a new instance of the class Transform.
     *
     * @param position the starting position of the entity
     * @param rotation the rotation of the entity
     */
    public TransformImpl(final Point2D position, final double rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final double x, final double y) {
        this.position = this.position.add(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveOnGroundLevel() {
        if (this.isUnderGroundLevel()) {
            this.position = new Point2D(this.position.getX(), yGround);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGroundLevel() {
        return this.yGround;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroundLevel(final double yGround) {
        this.yGround = yGround;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnderGroundLevel() {
        return this.position.getY() > yGround;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGroundLevel() {
        setGroundLevel(Window.getHeight());
        moveOnGroundLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveTo(final double x, final double y) {
        this.position = new Point2D(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRotation() {
        return this.rotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotation(final double rotation) {
        this.rotation = rotation;
    }

    /**
     * {@inheritDoc}
     */
    public Transform copyOf() {
        return new TransformImpl(
                new Point2D(this.getPosition().getX(), this.getPosition().getY()),
                this.getRotation()
        );
    }


}
