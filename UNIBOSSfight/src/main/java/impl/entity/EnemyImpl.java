package impl.entity;

import core.component.Collider;
import core.component.Transform;
import core.entity.Bullet;
import core.entity.Enemy;
import impl.component.ColliderImpl;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;
import util.Acceleration;

/**
 * This class implements the enemy.
 */
public class EnemyImpl extends Enemy {

    private transient double xSpeed = 0;
    private transient double ySpeed = 0;

    /**
     * Creates a new instance of the class EnemyImpl.
     *
     * @param position the position of the enemy
     * @param height the height of the enemy
     * @param width the width of the enemy
     * @param filename the name of the file containing
     *                 the sprite of the enemy
     */
    public EnemyImpl(final Transform position, final int height, final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.ALICEBLUE, filename));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

        switch (input) {
            case LEFT -> {
                this.xSpeed =  Acceleration.accelerate(this.xSpeed, -3, 1);
                setDirection(-1);
            }
            case RIGHT -> {
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 3, 1);
                setDirection(1);
            }
            case SPACE -> {
                if (!isJumping()) {
                    this.ySpeed = -30;
                    getTransform().move(0, -1);
                }
            }
            case EMPTY -> {
                getTransform().move(this.xSpeed, ySpeed);
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 0, 0.5);
                this.ySpeed = this.isJumping()
                        ? Acceleration.accelerate(this.ySpeed, 20, 1) : 0;
            }
        }

        getTransform().resetGroundLevel();
        getHitbox().update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() < getTransform().getGroundLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initCollider() {
        final var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) > 0) {
                this.ySpeed = 0;
            }
            this.update(Inputs.SPACE);
            //setDirection((int) getHitbox().getCollisionSideOnX(e.getPosition().getX()));
        });

        collider.addBehaviour(Collider.Entities.PLAYER, e -> {

        collider.addBehaviour(Collider.Entities.BULLET, e -> {
            final Bullet b = (Bullet) e;
            getHealth().damage(b.getDamage());
        });

        setCollider(collider);
    }

}
