package impl.entity;

import core.component.Collider;
import core.component.Transform;
import core.entity.Enemy;
import impl.component.ColliderImpl;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

import java.util.Optional;

public class EnemyImpl extends Enemy {

    private static final int COLLISION_DAMAGE = 5;

    public EnemyImpl(final Transform position, final int height, final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.ALICEBLUE, filename));
    }

    @Override
    public void update(final Inputs input) {
        this.position.move(this.direction, 0);
        this.hitbox.update(getPosition());
    }

    @Override
    protected void initCollider() {
        var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.PLATFORM, e -> {
            this.direction = this.direction * -1;
            this.position.move(getDirection() * 5, 0);
        });

        this.collider = Optional.of(collider);
    }
}
