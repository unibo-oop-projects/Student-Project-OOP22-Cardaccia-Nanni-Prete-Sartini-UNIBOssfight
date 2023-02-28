package impl.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import core.entity.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import java.lang.Math;

public class BulletImpl extends Bullet {

    public BulletImpl(Transform startingPos, int height, int width, Renderer renderer, int damage, Point2D target) {
        super(startingPos, height, width, renderer, damage, target);
    }
}
