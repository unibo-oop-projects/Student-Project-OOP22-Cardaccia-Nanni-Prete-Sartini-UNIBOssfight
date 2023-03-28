package app.impl.entity;

import app.core.component.Collider;
import app.core.component.Transform;
import app.core.entity.Bullet;
import app.core.entity.Enemy;
import app.impl.component.ColliderImpl;
import app.impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class implements the enemy.
 */
public class EnemyImpl extends Enemy {

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
        maxXSpeed = 5;
        maxYSpeed = 20;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initCollider() {
        final var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) < 0) {
                setYSpeed(0);
            }
            this.update(Inputs.SPACE);
        });

        collider.addBehaviour(Collider.Entities.BULLET, e -> {
            final Bullet b = (Bullet) e;
            getHealth().damage(b.getDamage());
        });

        setCollider(collider);
    }

}
