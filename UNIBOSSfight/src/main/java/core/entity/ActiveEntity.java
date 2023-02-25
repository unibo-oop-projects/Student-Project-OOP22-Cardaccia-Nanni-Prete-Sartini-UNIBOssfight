package core.entity;

import core.component.Renderer;
import core.component.Transform;
import impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class ActiveEntity implements Entity {

    public enum Inputs {
        LEFT,
        RIGHT,
        SPACE,
        EMPTY
    }

    private int health;
    private Transform position;

    protected Renderer renderer;

    public ActiveEntity(int health, Transform position) {
        this.health = health;
        this.position = position;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void update(PlayerImpl.Inputs input) {
        switch (input) {
            case LEFT -> this.position.move(-5, 0);
            case RIGHT -> this.position.move(5, 0);
            case SPACE -> this.position.move(0, -5);
        }
    }

    @Override
    public Point2D getPosition() {
        return this.position.getPosition();
    }

    public void render(GraphicsContext gc, Point2D position) {
        this.renderer.render(gc, this.getPosition().subtract(position).add(300, position.getY()));
    }

    public boolean isDisplayed(Point2D playerPosition) {
        return this.getPosition().subtract(playerPosition).getX() < 300;
    }
}
