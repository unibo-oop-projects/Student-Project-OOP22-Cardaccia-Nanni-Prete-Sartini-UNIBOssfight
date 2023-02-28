package core.component;

import javafx.geometry.Point2D;

public abstract class Hitbox implements Component {

    private double lateralOffset;
    private double height;
    private Point2D position;
    private double leftSide, rightSide, topSide, bottomSide;

    public Hitbox(double latOffset, double height, Point2D startingPosition){
        this.lateralOffset = latOffset;
        this.height = height;
        this.position = startingPosition;

        findBorders(this.position);
    }

    public boolean collide(Hitbox target){
        //X an Y axis collisions
        return this.rightSide >= target.leftSide && this.leftSide <= target.rightSide && this.bottomSide <= target.topSide && this.topSide >= target.bottomSide;
    }

    public void update(Point2D newPos){
        this.position = newPos;

        findBorders(this.position);
    }

    private void findBorders(Point2D pos){
        this.leftSide = pos.getX() - this.lateralOffset;
        this.rightSide = pos.getX() + this.lateralOffset;
        this.topSide = pos.getY() - this.height;
        this.bottomSide = pos.getY();
    }
}
