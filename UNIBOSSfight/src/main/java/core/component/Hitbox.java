package core.component;

import util.Vector2d;

public abstract class Hitbox implements Component {

    private double lateralOffset;
    private double height;
    private Vector2d position;
    private double leftSide, rightSide, topSide, bottomSide;

    public Hitbox(double latOffset, double height, Vector2d startingPosition){
        this.lateralOffset = latOffset;
        this.height = height;
        this.position = startingPosition;

        findBorders(this.position);
    }

    boolean collide(Hitbox target){
        //X an Y axis collisions
        return this.rightSide >= target.leftSide && this.leftSide <= target.rightSide && this.bottomSide <= target.topSide && this.topSide >= target.bottomSide;
    }

    public void update(Vector2d newPos){
        this.position = newPos;

        findBorders(this.position);
    }

    private void findBorders(Vector2d pos){
        this.leftSide = pos.getX() - this.lateralOffset;
        this.rightSide = pos.getX() + this.lateralOffset;
        this.topSide = pos.getY() + this.height;
        this.bottomSide = pos.getY();
    }
}
