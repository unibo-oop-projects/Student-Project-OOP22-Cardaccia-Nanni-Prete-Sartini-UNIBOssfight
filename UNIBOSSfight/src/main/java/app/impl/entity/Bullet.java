package app.impl.entity;

import app.core.component.Collider;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.ActiveEntity;
import app.impl.component.ColliderImpl;
import app.impl.entity.Platform;
import app.impl.entity.Wall;
import app.util.Angle;
import javafx.geometry.Point2D;

/**
 * This class models the bullet shot by the weapons
 * of the game, which can cause damage, has a
 * speed and shifts along a vector.
 */
public class Bullet extends ActiveEntity {

    private final double xShift;
    private final double yShift;
    private final boolean isPlayerBullet;

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
     * @param isPlayerBullet identifier of the Bullets from the player Weapon
     */
    public Bullet(final Transform startingPos, final int height, final int width,
                  final Renderer renderer, final int damage, final Point2D target,
                  final int speed, final boolean isPlayerBullet) {

        super(startingPos, height, width, renderer);
        this.setDamage(damage);
        this.isPlayerBullet = isPlayerBullet;

        final double angle = Angle.findAngle(this.getPosition(), target);
        this.xShift = speed * Math.cos(angle);
        this.yShift = -speed * Math.sin(angle);

        this.getTransform().setRotation(Math.toDegrees(angle));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {
        if (input == Inputs.EMPTY) {
            getTransform().move(xShift, yShift);
            this.getHitbox().update(this.getPosition());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        final Collider collider = new ColliderImpl();

        collider.addBehaviour(Wall.class.getName(), e -> this.getHealth().destroy());

        collider.addBehaviour(Platform.class.getName(), e -> {
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) > 0) {
                this.getHealth().destroy();
            }
        });

        setCollider(collider);
    }

    /**
     * This method returns the Bullet property identifier.
     *
     * @return returns true if this is a Player Bullet
     */
    public boolean isPlayerBullet() {
        return this.isPlayerBullet;
    }
}
