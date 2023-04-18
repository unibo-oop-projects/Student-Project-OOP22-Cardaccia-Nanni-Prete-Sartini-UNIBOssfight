package app.core.entity;

import app.core.component.Behaviour;
import app.core.component.Collider;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.ColliderImpl;
import app.impl.entity.Platform;
import app.util.Acceleration;
import javafx.geometry.Point2D;
import app.util.Window;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Optional;

/**
 * This class models an Entity that can move and performs actions.
 */
public abstract class ActiveEntity extends AbstractEntity {

    private transient Behaviour behaviour;
    private transient Collider collider;
    private transient double xSpeed;
    private transient double ySpeed;
    private double maxXSpeed;
    private double maxYSpeed;

    /**
     * Creates a new instance of the abstract class AbstractEntity.
     *
     * @param position the position of the entity
     * @param height   the height of the entity
     * @param width    the width of the entity
     * @param renderer the renderer of the entity
     */
    public ActiveEntity(final Transform position, final int height, final int width, final Renderer renderer) {
        super(position, height, width, renderer);
        this.behaviour = new BehaviourBuilderImpl().build();
    }

    /**
     * Gets the maximum movement speed on x-axis.
     *
     * @return a double representing the maximum speed on x-axis
     */
    public double getMaxXSpeed() {
        return this.maxXSpeed;
    }

    /**
     * Gets the maximum movement speed on x-axis reachable from the entity.
     *
     * @param maxXSpeed the maximum speed
     */
    public void setMaxXSpeed(final double maxXSpeed) {
        this.maxXSpeed = maxXSpeed;
    }

    /**
     * Gets the maximum movement speed on y-axis.
     *
     * @return a double representing the maximum speed on y-axis
     */
    public double getMaxYSpeed() {
        return this.maxYSpeed;
    }

    /**
     * Gets the maximum movement speed on y-axis reachable from the entity.
     *
     * @param maxYSpeed the maximum speed
     */
    public void setMaxYSpeed(final double maxYSpeed) {
        this.maxYSpeed = maxYSpeed;
    }

    /**
     * Sets the movement speed on x-axis.
     *
     * @param xSpeed the new value of the speed
     */
    public void setXSpeed(final double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * Returns the max xSpeed of the entity.
     *
     * @return the max xSpeed
     */
    public double getXSpeed() {
        return xSpeed;
    }

    /**
     * Sets the movement speed on y-axis.
     *
     * @param ySpeed the new value of the speed
     */
    public void setYSpeed(final double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * This method returns the Collider of the entity.
     *
     * @return the Collider of the entity
     */
    public Optional<Collider> getCollider() {
        return Optional.ofNullable(this.collider);
    }

    /**
     * Manages the collision of the entity.
     *
     * @param e the entity collided
     */
    public void manageCollision(final Entity e) {
        this.getCollider().ifPresent(x -> x.manageCollision(e));
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
     * This method returns the behaviour of the entity.
     *
     * @return the component Behaviour
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "It is needed because it is necessary"
                    + " to modify the state of the object from the outside"
    )
    public Behaviour getBehaviour() {
        return this.behaviour;
    }

    /**
     * Sets the behaviour of the entity.
     *
     * @param behaviour the new behaviour
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "It is needed because it is necessary"
                    + " to modify the state of the object from the outside"
    )
    public void setBehaviour(final Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * Takes as input an element of Inputs enum and,
     * from that, the class will do the update.
     *
     * @param input an element of the enum
     */
    public void update(final Inputs input) {
        switch (input) {
            case LEFT -> {
                this.xSpeed =  Acceleration.accelerate(this.xSpeed, -maxXSpeed, 1);
                setDirection(-1);
            }
            case RIGHT -> {
                this.xSpeed = Acceleration.accelerate(this.xSpeed, maxXSpeed, 1);
                setDirection(1);
            }
            case SPACE -> jump();

            case EMPTY -> {
                getTransform().move(this.xSpeed, this.ySpeed);
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 0, 0.5);
                this.ySpeed = this.isJumping()
                        ? Acceleration.accelerate(this.ySpeed, -maxYSpeed, 1) : 0;
                getTransform().resetGroundLevel();
                getHitbox().update(this.getPosition());
            }
            default -> throw new IllegalStateException();
        }
    }

    /**
     * This method checks if the entity should be updated based on
     * its position and the distance from the position of the player.
     *
     * @param position the position of the player
     * @return true if the entity should be updated,
     *         false otherwise
     */
    public boolean isUpdated(final Point2D position) {
        return Math.abs(this.getPosition().subtract(position).getX()) < Window.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        final Collider collider = new ColliderImpl();

        collider.addBehaviour(Platform.class.getName(), e -> Platform.jump(this, e));

        setCollider(collider);
    }

    /**
     * Makes the entity jump.
     */
    protected void jump() {
        if (!isJumping()) {
            this.ySpeed = this.getMaxYSpeed();
            getTransform().move(0, 1);
        }
    }

    /**
     * Checks if the Entity is jumping.
     *
     * @return true if the y position of the entity is over the ground level,
     * false otherwise
     */
    protected boolean isJumping() {
        return this.getPosition().getY() > getTransform().getGroundLevel();
    }
}
