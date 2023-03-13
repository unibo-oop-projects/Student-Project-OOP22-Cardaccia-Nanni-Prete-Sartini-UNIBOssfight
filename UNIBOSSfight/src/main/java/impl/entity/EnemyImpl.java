package impl.entity;

import core.component.Collider;
import core.component.Transform;
import core.entity.Bullet;
import core.entity.Enemy;
import impl.component.ColliderImpl;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class implements the enemy.
 */
public class EnemyImpl extends Enemy {

    private static final int COLLISION_DAMAGE = 5;

    /**
     * Creates a new instance of the class EnemyImpl.
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
        getTransform().move(getDirection(), 0);
        getHitbox().update(getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initCollider() {
        final var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.PLATFORM, e -> {
            setDirection(getDirection() * -1);
            getTransform().move(getDirection() * 5, 0);
        });

        collider.addBehaviour(Collider.Entities.BULLET, e -> {
            final Bullet b = (Bullet) e;
            getHealth().damage(b.getDamage());
            b.getHealth().damage(b.getHealth().getValue());
        });

        setCollider(collider);
    }
}
