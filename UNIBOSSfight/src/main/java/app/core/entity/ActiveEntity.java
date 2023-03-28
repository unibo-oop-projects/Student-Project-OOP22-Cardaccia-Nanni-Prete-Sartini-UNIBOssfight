package app.core.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.util.Acceleration;

import java.util.ArrayList;
import java.util.List;

public abstract class ActiveEntity extends AbstractEntity {

    private transient double xSpeed = 0;
    private transient double ySpeed = 0;
    protected double maxXSpeed = 0;
    protected double maxYSpeed = 0;
    private transient final List<Bullet> bullets = new ArrayList<>();

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
     * Sets the movement speed on y-axis.
     *
     * @param ySpeed the new value of the speed
     */
    public void setYSpeed(final double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Inputs input) {
        switch (input) {
            case LEFT -> {
                this.xSpeed =  Acceleration.accelerate(this.xSpeed, -maxXSpeed, 1);
                setDirection(-1);
            }
            case RIGHT -> {
                this.xSpeed = Acceleration.accelerate(this.xSpeed, maxXSpeed, 1);
                setDirection(1);
            }
            case SPACE -> {
                if (!isJumping()) {
                    this.ySpeed = 30;
                    getTransform().move(0, 1);
                }
            }
            case EMPTY -> {
                getTransform().move(this.xSpeed, this.ySpeed);
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 0, 0.5);
                this.ySpeed = this.isJumping()
                        ? Acceleration.accelerate(this.ySpeed, -maxYSpeed, 1) : 0;
                this.bullets.forEach(e -> e.update(Inputs.EMPTY));
                removeBullets();
            }
        }

        getTransform().resetGroundLevel();
        getHitbox().update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() > getTransform().getGroundLevel();
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
     * Adds a new bullet to the shot ones.
     *
     * @param bullet the shot bullet
     */
    public void addBullet(final Bullet bullet) {
        this.bullets.add(bullet);
    }

    /**
     * Removes from the list the bullets that should not be displayed anymore.
     */
    protected void removeBullets() {
        this.bullets.removeIf(e -> !e.isDisplayed(this.getPosition())
                || e.getHealth().isDead());
    }
}
