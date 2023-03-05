package core.component;

import javafx.geometry.Point2D;
import util.Window;

public class Transform implements Component {

    private Point2D position;
    private float rotation;

    private double yGround = Window.getHeight(); // TODO height della window

    public Transform(Point2D position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void move (int x, int y) {
        this.position = this.position.add(x, y);
    }

    public void setGroundLevel(){
        if(this.isUnderGroundLevel()) {
            this.position = new Point2D(this.position.getX(), yGround);
        }
    }

    public double getGroundLevel() {
        return this.yGround;
    }

    public void setGroundLevel(final double yGround) {
        this.yGround = yGround;
    }

    public boolean isUnderGroundLevel(){
        return this.position.getY() > yGround;
    }

    public void resetGroundLevel() {
        setGroundLevel(Window.getHeight());
        setGroundLevel();
    }

    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    public static Transform copyOf(Transform input) {
        return new Transform(new Point2D(input.getPosition().getX(), input.getPosition().getY()), input.rotation);
    }

    public void moveTo(final double x, final double y) {
        this.position = new Point2D(x, y);
    }
}
