package app.impl.entity;

import app.core.component.Collider;
import app.core.component.Transform;
import app.core.entity.Enemy;
import app.impl.builder.BehaviourBuilderImpl;
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
        // TODO da togliere, compito della serializzazione
        setMaxXSpeed(5);
        setMaxYSpeed(20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        setBehaviour(new BehaviourBuilderImpl()
                .addJumpOnTop()
                .addStopFromBottom()
                .addStopFromSide()
                .addFollow()
                .build());

        final var collider = new ColliderImpl();

        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            this.update(Inputs.SPACE);
        });

        collider.addBehaviour(Collider.Entities.PLATFORM, e -> Platform.jump(this, e));

        collider.addBehaviour(Collider.Entities.BULLET, e -> {
            if (!e.getHealth().isDead()) {
                getHealth().damage(e.getDamage());
            }
            e.getHealth().destroy();
        });

        setCollider(collider);
    }

}
