package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.entity.Enemy;
import core.entity.Entity;
import impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Optional;

public class EnemyImpl extends Enemy {

    private static final int COLLISION_DAMAGE = 5;

    public EnemyImpl(Transform position, int width, int height, String filename) {
        super(position, width, height,
                new SpriteRenderer(height, width, Color.ALICEBLUE, filename));
    }

    @Override
    public void update(Inputs input) {

    }
}
