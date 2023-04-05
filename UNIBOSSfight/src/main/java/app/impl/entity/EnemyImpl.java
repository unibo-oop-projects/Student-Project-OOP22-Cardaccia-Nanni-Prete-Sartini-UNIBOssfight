package app.impl.entity;

import app.core.component.Transform;
import app.core.entity.Enemy;
import app.impl.builder.BehaviourBuilderImpl;
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
