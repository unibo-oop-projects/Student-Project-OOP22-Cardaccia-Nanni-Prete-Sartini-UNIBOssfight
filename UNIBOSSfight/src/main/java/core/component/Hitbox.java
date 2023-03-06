package core.component;

import javafx.geometry.Point2D;

public class Hitbox implements Component {

    private double lateralOffset;
    private double height;
    private Point2D position;
    private double leftSide, rightSide, topSide, bottomSide;

    public Hitbox(final double latOffset, final double height,
                  final Point2D startingPosition) {
        this.lateralOffset = latOffset;
        this.height = height;
        this.position = startingPosition;

        findBorders(this.position);
    }

    public boolean collide(final Hitbox target) {
        //X an Y axis collisions
        return this.rightSide >= target.leftSide
                && this.leftSide <= target.rightSide
                && this.bottomSide >= target.topSide
                && this.topSide <= target.bottomSide;
    }

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
