package impl.entity;

import core.component.Renderer;
import core.component.Transform;
import core.entity.Bullet;
import javafx.geometry.Point2D;

public class BulletImpl extends Bullet {

    public BulletImpl(Transform startingPos, int height, int width, Renderer renderer, int damage, Point2D target, int speed) {
        super(startingPos, height, width, renderer, damage, target, speed);
    }
}
