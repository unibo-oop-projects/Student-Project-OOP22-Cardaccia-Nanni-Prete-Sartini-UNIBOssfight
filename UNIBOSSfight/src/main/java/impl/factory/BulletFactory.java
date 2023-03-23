package impl.factory;

import core.component.Collider;
import core.component.Transform;
import impl.component.TransformImpl;
import core.entity.Bullet;
import impl.component.SpriteRenderer;
import impl.entity.BulletImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BulletFactory {

    public Bullet getPlayerBullet(Transform playerPos, Point2D target) {

        Transform startingPosCopy = playerPos.copyOf();
        startingPosCopy.move(0, -125);

        return new BulletImpl(startingPosCopy,  20, 20,
                new SpriteRenderer(20, 20, Color.BLACK, "testImage2.png"),
                100, target, 20) {
            @Override
            public void initCollider() {
                super.initCollider();

                getCollider().get().addBehaviour(Collider.Entities.ENEMY, e -> {
                    this.getHealth().destroy();
                });
            }
        };
    }

    public Bullet getBigBullet(Transform startingPos, Point2D target) {
        return new BulletImpl(startingPos, 40, 40,
                new SpriteRenderer(40, 40, Color.BLACK, "testImage2.png"), 200, target, 5);
    }

}
