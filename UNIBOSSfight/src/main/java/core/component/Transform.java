package core.component;

import javafx.geometry.Point2D;

public abstract class Transform implements Component {

    private Point2D position;
    private float rotation;

    private int yGround = 600; // TODO height della window

    public Transform(Point2D position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public abstract void update();

    public void move (int x, int y) {
        this.position = this.position.add(x, y);
    }

    public void setGroundLevel(){
        if(this.isUnderGroundLevel()) {
            this.position = new Point2D(this.position.getX(), yGround);
        }
    }

    public int getGroundLevel() {
        return this.yGround;
    }

    public void setGroundLevel(int yGround) {
        this.yGround = yGround;
    }

    public boolean isUnderGroundLevel(){
        return this.position.getY() > yGround;
    }

    public void resetGroundLevel() {
        setGroundLevel(600); // TODO heigth della window
    }

    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    public void moveTo(int x, int y) {
        this.position = new Point2D(x, y);
    }
}
