package app.core.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.impl.entity.Bullet;
import app.impl.entity.Wall;

/**
 * This class models the enemy of the game, which causes damage
 * to the player and can be killed by him.
 */
public abstract class Enemy extends ActiveEntity {

    /**
     * The constructor of the class.
     *
     * @param position the position of the enemy
     * @param height the height of the enemy
     * @param width the width of the enemy
     * @param renderer the renderer of the enemy
     */
    public Enemy(final Transform position, final int height,
                 final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        getCollider().ifPresent(c -> c.addBehaviour(Wall.class.getName(), e -> {
            Wall.stop(this, e);
            if (getHitbox().getCollisionSideOnY(e.getPosition().getY()) == 0) {
                jump();
            }
        }));

        getCollider().ifPresent(c -> c.addBehaviour(Bullet.class.getName(), e -> {
            final Bullet b = (Bullet) e;
            if (!b.getHealth().isDead() && b.isPlayerBullet()) {
                getRenderer().setIsDamaged();
                getHealth().damage(b.getDamage());
                b.getHealth().destroy();
            }

        }));
    }
}
