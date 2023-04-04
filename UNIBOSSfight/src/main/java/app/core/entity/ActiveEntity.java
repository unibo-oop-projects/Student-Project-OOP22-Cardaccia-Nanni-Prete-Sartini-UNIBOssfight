package app.core.entity;

import app.core.component.Behaviour;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.impl.builder.BehaviourBuilderImpl;
import app.util.Acceleration;
import app.util.Window;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models an Entity that can move and performs actions.
 */
public abstract class ActiveEntity extends AbstractEntity {

    private transient Behaviour behaviour;
    private transient double xSpeed;
    private transient double ySpeed;
    private double maxXSpeed;
    private double maxYSpeed;
    private transient List<Bullet> bullets = new ArrayList<>();

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
     * This method returns the behaviour of the entity.
     *
     * @return the component Behaviour
     */
    public Behaviour getBehaviour() {
        return this.behaviour;
    }

    /**
     * Sets the behaviour of the entity.
     *
     * @param behaviour the new behaviour
     */
    public void setBehaviour(final Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * This method returns the list of bullets shot by the entity.
     *
     * @return the list of bullet
     */
    public List<Bullet> getBullets() {
        return new ArrayList<>(this.bullets);
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
                this.bullets.forEach(Bullet::update);
                removeBullets();
            }
            default -> throw new IllegalStateException();
        }

        getTransform().resetGroundLevel();
        getHitbox().update(this.getPosition());
    }

    /**
     * Adds a new bullet to the shot ones.
     *
     * @param bullet the shot bullet
     */
    public void addBullet(final Bullet bullet) {
        this.bullets.add(bullet);
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
        this.bullets = new ArrayList<>();
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

    private boolean isJumping() {
        return this.getPosition().getY() > getTransform().getGroundLevel();
    }

    private void removeBullets() {
        this.bullets.removeIf(e -> !e.isDisplayed(this.getPosition())
                || e.getHealth().isDead());
    }
}
