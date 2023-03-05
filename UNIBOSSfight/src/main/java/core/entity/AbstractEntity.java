package core.entity;

import core.component.Collider;
import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import util.Window;

import java.util.Optional;

public abstract class AbstractEntity implements Entity {

    private final int height;
    private final int width;
    private Optional<Integer> damage;

    protected Transform position;
    protected Hitbox hitbox;
    protected Renderer renderer;
    protected Optional<Collider> collider;

    protected int direction = 1;

    public AbstractEntity(
            final Transform position,
            final int height,
            final int width,
            final Renderer renderer
    ) {
        this.position = position;
        this.renderer = renderer;
        this.height = height;
        this.width = width;

        this.hitbox = new Hitbox(width / 2.0, height, getPosition()) {
        };

        initCollider();
    }

    public abstract void update(Inputs input);

    public Point2D getPosition() {
        return this.position.getPosition();
    }

    @Override
    public Node render(Point2D position) {
        return this.renderer.render(
                new Point2D(
                        this.getPosition()
                                .subtract(position)
                                .add(Window.getWidth() / 2, 0)
                                .getX(),
                        this.getPosition().getY() - 57
                ),
                this.getDirection(),
                0
        );
    }

    @Override
    public boolean isDisplayed(Point2D playerPosition) {
        //System.out.println(this.getClass() + " " + this.getPosition().getY() + " " + Window.getHeight());
        return Math.abs(this.getPosition().subtract(playerPosition).getX()) < Window.getWidth() / 2 &&
                this.getPosition().getY() <= Window.getHeight() &&
                this.getPosition().getY() > 0;
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

    protected void initCollider() {
        this.collider = Optional.empty();
    }

    public Optional<Collider> getCollider() {
        return this.collider;
    }

    public void manageCollision(Entity e) {
        this.collider.ifPresent(x -> x.manageCollision(e));
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
