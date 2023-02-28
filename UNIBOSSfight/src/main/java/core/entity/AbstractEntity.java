package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Optional;

public abstract class AbstractEntity implements Entity {

    private final int height;
    private final int width;
    private Optional<Integer> damage;

    protected Transform position;
    protected Hitbox hitbox;
    protected Renderer renderer;

    protected int direction = 1;

    public AbstractEntity(final Transform position, final int height,
                          final int width, final Renderer renderer) {
        this.position = position;
        this.renderer = renderer;
        this.height = height;
        this.width = width;

        this.hitbox = new Hitbox(width / 2.0, height, getPosition()) {
            @Override
            public void update() {

            }
        };
    }

    public abstract void update(Inputs input);

    public Point2D getPosition() {
        return this.position.getPosition();
    }

    public void render(GraphicsContext gc, Point2D position) {
        this.renderer.render(gc, new Point2D(this.getPosition().subtract(position).add(300, 0).getX(), this.getPosition().getY()), this.getDirection());
    }

    public boolean isDisplayed(Point2D playerPosition) {
        return this.getPosition().subtract(playerPosition).getX() < 300;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    protected int getDirection() {
        return this.direction;
    }

    public Optional<Integer> getDamage() {
        return this.damage;
    }

    protected void setDamage(Optional<Integer> damage) {
        this.damage = damage;
    }


}
