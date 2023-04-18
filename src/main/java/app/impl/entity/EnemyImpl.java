package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.Enemy;
import app.impl.builder.BehaviourBuilderImpl;

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
     * @param renderer the renderer of the entity
     */
    public EnemyImpl(final Transform position, final int height, final int width, final Renderer renderer) {
        super(position, height, width, renderer);
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


    }

}
