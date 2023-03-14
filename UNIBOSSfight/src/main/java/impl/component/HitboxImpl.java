package impl.component;

import core.component.Hitbox;
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
    public void update(final Point2D newPos) {
        this.position = newPos;

        findBorders(this.position);
    }

    private void findBorders(final Point2D pos) {
        this.leftSide = pos.getX() - this.lateralOffset;
        this.rightSide = pos.getX() + this.lateralOffset;
        this.topSide = pos.getY() - this.height;
        this.bottomSide = pos.getY();
   }
}
