package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.entity.Enemy;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class EnemyImpl extends Enemy {

    private static final int COLLISION_DAMAGE = 5;

    public EnemyImpl(int health, Transform position) {
        super(health, position);
    }

    public int getDamage() {
        return COLLISION_DAMAGE;
    }

    @Override
    public void update() {

    }

}
