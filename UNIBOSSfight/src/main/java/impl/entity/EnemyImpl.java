package impl.entity;

import core.component.Collider;
import core.component.Transform;
import core.entity.Enemy;
import impl.component.ColliderImpl;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

public class EnemyImpl extends Enemy {

    private static final int COLLISION_DAMAGE = 5;

    public EnemyImpl(final Transform position, final int height, final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.ALICEBLUE, filename));
    }

    @Override
    public void update(final Inputs input) {
        getTransform().move(getDirection(), 0);
        getHitbox().update(getPosition());
    }

    @Override
    protected void initCollider() {
        final var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.PLATFORM, e -> {
            setDirection(getDirection() * -1);
            getTransform().move(getDirection() * 5, 0);
        });

        setCollider(collider);
    }
}
