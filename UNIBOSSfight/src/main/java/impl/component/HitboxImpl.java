package impl.component;

import core.component.Hitbox;
import core.entity.Entity;
import javafx.geometry.Point2D;

/**
 * This class implements the Hitbox.
 */
public class HitboxImpl implements Hitbox {

    private final double lateralOffset;
    private final double height;
    private Point2D position;
    private double leftSide, rightSide, topSide, bottomSide;

    /**
     * Creates a new instance of the class Hitbox.
     * @param latOffset the offset used to build the rectangle
     *                  which represents the hitbox
     * @param height the height of the entity
     * @param startingPosition the initial position of the entity
     */
    public HitboxImpl(final double latOffset, final double height,
                      final Point2D startingPosition) {
        this.lateralOffset = latOffset;
        this.height = height;
        this.position = startingPosition;

        findBorders(this.position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLeftSide() {
        return this.leftSide;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRightSide() {
        return this.rightSide;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTopSide() {
        return this.topSide;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBottomSide() {
        return this.bottomSide;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean collide(final Hitbox target) {
        //X an Y axis collisions
        return this.rightSide >= target.getLeftSide()
                && this.leftSide <= target.getRightSide()
                && this.bottomSide >= target.getTopSide()
                && this.topSide <= target.getBottomSide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Point2D newPos) {
        this.position = newPos;

        findBorders(this.position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCollisionSideOnY(final double y) {
        return Math.signum(this.position.getY() - y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCollisionSideOnX(final double x) {
        return Math.signum(this.position.getX() - x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getIntersectionOnY(final Entity e) {
        final double side = this.getCollisionSideOnY(e.getPosition().getY());

        final double collidedSide = side > 0 ? e.getHitbox().getTopSide()
                : e.getPosition().getY();
        final double collidingSide = side > 0 ? this.topSide : this.position.getY();

        return e.getHeight() + (collidedSide - collidingSide) * side;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getIntersectionOnX(final Entity e) {
        final double side = getCollisionSideOnX(e.getPosition().getX());

        final double collidedSide = side > 0 ? e.getHitbox().getRightSide() : e.getHitbox().getLeftSide();
        final double collidingSide = side > 0 ? this.leftSide : this.rightSide;

        return collidedSide - collidingSide;
    }

    private void findBorders(final Point2D pos) {
        this.leftSide = pos.getX() - this.lateralOffset;
        this.rightSide = pos.getX() + this.lateralOffset;
        this.topSide = pos.getY() - this.height;
        this.bottomSide = pos.getY();
   }
}
