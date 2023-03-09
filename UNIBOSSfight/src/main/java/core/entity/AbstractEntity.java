package core.entity;

import core.component.Collider;
import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import util.Window;

import java.util.Optional;

/**
 * This class is an implementation of Entity.
 */
public abstract class AbstractEntity implements Entity {
    private final int height;
    private final int width;
    private int damage;
    private final Transform position;
    private final Hitbox hitbox;
    private final Renderer renderer;
    private Optional<Collider> collider;
    private int direction;

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
        this.position = Transform.copyOf(position);
        this.renderer = renderer;
        this.height = height;
        this.width = width;
        this.direction = 1;
        this.hitbox = new Hitbox(width / 2.0, height, getPosition());

        initCollider();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position.getPosition();
    }

    /**
     * @return the Renderer of the entity
     */
    protected Renderer getRenderer() {
        return this.renderer;
    }

    /**
     * @return the Transform of the entity
     */
    protected Transform getTransform() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * @return the direction of the entity
     */
    protected int getDirection() {
        return this.direction;
    }

    /**
     * Assigns to the entity its direction.
     * @param direction the direction of the entity
     */
    protected void setDirection(final int direction) {
        this.direction = direction;
    }

    /**
     * @return the inflicted damage to the entity
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Assigns to the entity the damage that it inflicts.
     * @param damage harm inflicted
     */
    protected void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Collider> getCollider() {
        return this.collider;
    }

    /**
     * Assigns to the entity its collider.
     * @param collider the collider of the entity
     */
    protected void setCollider(final Collider collider) {
        this.collider = Optional.ofNullable(collider);
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
                0
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed(final Point2D playerPosition) {
        //System.out.println(this.getClass() + " " + this.getPosition().getY() + " " + Window.getHeight());
        return Math.abs(this.getPosition().subtract(playerPosition).getX()) < Window.getWidth() / 2
                && this.getPosition().getY() <= Window.getHeight()
                && this.getPosition().getY() > 0;
    }

    /**
     * Initialise the collider of the entity.
     */
    protected void initCollider() {
        this.collider = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageCollision(final Entity e) {
        this.collider.ifPresent(x -> x.manageCollision(e));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void update(Inputs input);

}
