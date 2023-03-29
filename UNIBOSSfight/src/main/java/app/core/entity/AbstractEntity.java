package app.core.entity;

import app.core.component.Collider;
import app.core.component.Health;
import app.core.component.Hitbox;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.impl.component.HitboxImpl;
import app.impl.component.HealthImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import app.util.Window;
import java.util.Optional;

/**
 * This class is an implementation of Entity.
 */
public abstract class AbstractEntity implements Entity {

    private final String className;
    private final int height;
    private final int width;
    private int damage;
    private transient int direction;
    private final Transform position;
    private transient Hitbox hitbox;
    private final Renderer renderer;
    private final Health health;
    private transient Collider collider;

    /**
     * Creates a new instance of the abstract class AbstractEntity.
     * @param position the position of the entity
     * @param height the height of the entity
     * @param width the width of the entity
     * @param renderer the renderer of the entity
     */
    public AbstractEntity(
            final Transform position,
            final int height,
            final int width,
            final Renderer renderer
    ) {
        this.className = this.getClass().getName();
        this.position = position.copyOf();
        this.renderer = renderer;
        this.health = new HealthImpl();
        this.height = height;
        this.width = width;
        this.direction = 1;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getTransform() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Health getHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * This method returns the Renderer of the entity.
     *
     * @return the Renderer of the entity
     */
    protected Renderer getRenderer() {
        return this.renderer;
    }

    /**
     * This method returns the direction of the entity.
     *
     * @return the direction of the entity
     */
    protected int getDirection() {
        return this.direction;
    }

    /**
     * Assigns to the entity its direction.
     *
     * @param direction the direction of the entity
     */
    protected void setDirection(final int direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    //TODO valutare se eliminare questo metodo
    @Override
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Collider> getCollider() {
        return Optional.ofNullable(this.collider);
    }

    /**
     * Assigns to the entity its collider.
     *
     * @param collider the collider of the entity
     */
    protected void setCollider(final Collider collider) {
        this.collider = collider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node render(final Point2D position) {
        return this.renderer.render(
                new Point2D(
                        this.getPosition()
                                .subtract(position)
                                .add(Window.getWidth() / 2, 0)
                                .getX(),
                        this.getPosition().getY()
                ),
                this.getDirection(),
                1,
                this.getTransform().getRotation()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed(final Point2D playerPosition) {
        return Math.abs(this.getPosition().subtract(playerPosition).getX()) - this.width / 2.0 < Window.getWidth() / 2
                && this.getPosition().getY() < Window.getHeight()
                && this.getPosition().getY() >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageCollision(final Entity e) {
        this.getCollider().ifPresent(x -> x.manageCollision(e));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.hitbox = new HitboxImpl(width / 2.0, height, this.position.getPosition());
        this.collider = null;
    }

}
