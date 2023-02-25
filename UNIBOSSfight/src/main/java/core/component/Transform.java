package core.component;

import javafx.geometry.Point2D;

public abstract class Transform implements Component {

    private Point2D position;
    private float rotation;

    public Transform(Point2D position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public abstract void update();

    public void move (int x, int y) {
        this.position = this.position.add(x, y);    }

    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }
}
