package core.component;

import util.Vector2d;

public abstract class Transform implements Component {

    private Vector2d position;
    private float rotation;

    public Transform(Vector2d position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public abstract void update();

    public void move (int x, int y) {
        this.position.translate(x, y);
    }

    public Vector2d getPosition() {
        return this.position.copyOf();
    }
}
