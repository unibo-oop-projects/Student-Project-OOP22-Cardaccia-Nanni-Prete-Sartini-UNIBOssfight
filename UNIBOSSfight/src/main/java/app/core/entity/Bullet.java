package app.core.entity;

import app.core.component.Collider;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.impl.component.ColliderImpl;
import javafx.geometry.Point2D;
import app.util.Window;

/**
 * This class models the bullet shot by the weapons
 * of the game, which can cause damage, has a
 * speed and shifts along a vector.
 */
public abstract class Bullet extends AbstractEntity {

    private final int speed;
    private final double xShift;
    private final double yShift;

    /**
     * Creates a new instance of the class Bullet.
     *
     * @param startingPos the starting position of the bullet
     * @param height the height of the bullet
     * @param width the width of the bullet
     * @param renderer the renderer of the bullet
     * @param damage the damage caused by bullet
     * @param target the spot giving the trajectory
     * @param speed the speed of the bullet
     */
    public Bullet(final Transform startingPos, final int height, final int width,
                  final Renderer renderer, final int damage, final Point2D target,
                  final int speed) {

        super(startingPos, height, width, renderer);
        this.setDamage(damage);
        this.speed = speed;

        // Finding vector angle
        final double dx = (target.getX() + getPosition().getX() - Window.getWidth() / 2)
                - getPosition().getX();
        final double dy = Window.getHeight() - target.getY() - getPosition().getY();
        final double angle = -Math.atan2(dy, dx);
        this.xShift = this.speed * Math.cos(angle);
        this.yShift = -this.speed * Math.sin(angle);

        this.getTransform().setRotation((float) Math.toDegrees(angle));
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Inputs input) {
        getTransform().move((int) xShift, (int) yShift);
        this.getHitbox().update(this.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initCollider() {
        final var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.PLATFORM, e -> this.getHealth().destroy());

        setCollider(collider);
    }
}
