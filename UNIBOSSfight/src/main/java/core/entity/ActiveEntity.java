package core.entity;

import core.component.Hitbox;
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
    protected Transform position;
    protected Hitbox hitbox;
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

    public Point2D getPosition() {
        return this.position.getPosition();
    }

    public void render(GraphicsContext gc, Point2D position) {
        this.renderer.render(gc, new Point2D(this.getPosition().subtract(position).add(300, 0).getX(), this.getPosition().getY()));
    }

    public boolean isDisplayed(Point2D playerPosition) {
        return this.getPosition().subtract(playerPosition).getX() < 300;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }


}
