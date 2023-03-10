package core.component;

import javafx.geometry.Point2D;

/**
 * This class models the hitbox component, which represents the
 * rectangular area occupied by the entity and is used to detect
 * the collisions between entities.
 */
public class Hitbox implements Component {

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
    public Hitbox(final double latOffset, final double height,
                  final Point2D startingPosition) {
        this.lateralOffset = latOffset;
        this.height = height;
        this.position = startingPosition;

        findBorders(this.position);
    }

    /**
     * Checks if there has been a collision between two entities
     * by controlling if the two hitboxes are overlapping.
     * @param target the hitbox of the entity on which the collision
     *               is being checked
     * @return true if the collision occurred, false otherwise
     */
    public boolean collide(final Hitbox target) {
        //X an Y axis collisions
        return this.rightSide >= target.leftSide
                && this.leftSide <= target.rightSide
                && this.bottomSide >= target.topSide
                && this.topSide <= target.bottomSide;
    }

    /**
     * Updates the hitbox by giving it a new position.
     * @param newPos new position of the hitbox
     */
    public void update(final Point2D newPos) {
        this.position = newPos;

        findBorders(this.position);
    }

    /**
     * Finds the coordinates of the new position of the hitbox,
     * so where it is located in the game window.
     * @param pos the new position of the hitbox
     */
    private void findBorders(final Point2D pos) {
        this.leftSide = pos.getX() - this.lateralOffset;
        this.rightSide = pos.getX() + this.lateralOffset;
        this.topSide = pos.getY() - this.height;
        this.bottomSide = pos.getY();
    }
}
