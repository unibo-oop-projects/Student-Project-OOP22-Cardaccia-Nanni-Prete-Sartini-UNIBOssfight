package core.component;

import util.Vector2d;

public abstract class Transform implements Component {

    private Vector2d position;
    private float rotation;

    public abstract void update();

    public Vector2d getPosition() {
        return this.position.copyOf();
    }
}
