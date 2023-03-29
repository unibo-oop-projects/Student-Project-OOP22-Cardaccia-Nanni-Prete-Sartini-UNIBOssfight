package app.impl.factory;

import app.core.component.Collider;
import app.core.component.Transform;
import app.core.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.entity.BulletImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BulletFactory {

    public Bullet getPlayerBullet(Transform playerPos, Point2D target) {

        return new BulletImpl(playerPos,  20, 20,
                new SpriteRenderer(20, 20, Color.BLACK, "bullet.png"),
                100, target, 20) {
            @Override
            public void init() {
                super.init();

                getCollider().ifPresent(c -> c.addBehaviour(Collider.Entities.ENEMY, e -> {
                    this.getHealth().destroy();
                }));
            }
        };
    }

    public Bullet getBigBullet(Transform startingPos, Point2D target) {
        return new BulletImpl(startingPos, 40, 40,
                new SpriteRenderer(40, 40, Color.BLACK, "bullet.png"), 200, target, 5);
    }

}
