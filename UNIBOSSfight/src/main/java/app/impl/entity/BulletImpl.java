package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.Bullet;
import javafx.geometry.Point2D;

public class BulletImpl extends Bullet {

    public BulletImpl(final Transform startingPos, final int height, final int width,
                      final Renderer renderer, final int damage, final Point2D target,
                      final int speed) {
        super(startingPos, height, width, renderer, damage, target, speed);
    }
}
