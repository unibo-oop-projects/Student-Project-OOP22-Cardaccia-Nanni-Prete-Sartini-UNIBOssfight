package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.entity.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import java.lang.Math;

public class BulletImpl implements Bullet {

    private final double RIGHT_ANGLE = 90;

    private int damage;
    private  int speed;
    private double angle;
    private Transform position;
    private Transform startingPosition;
    private Transform target;
    private Hitbox hitbox;

    public BulletImpl(Transform startingPos, Transform target, int damage, int speed, double angle, Hitbox hitbox){
        this.startingPosition = startingPos;
        this.position = startingPos;
        this.target = target;
        this.damage = damage;
        this.speed = speed;
        this.angle = angle;
        this.hitbox = hitbox;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean isDisplayed(Point2D position) {
        return false;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {

    }

    @Override
    public void update(Inputs input) {

        double deltaX, deltaY;
        double oppositeAngleX = RIGHT_ANGLE - angle;
        double oppositeAngleY = RIGHT_ANGLE - (RIGHT_ANGLE - angle);

        deltaX = this.speed * Math.cos(oppositeAngleX);
        deltaY = this.speed * Math.cos(oppositeAngleY);

        position.move((int)deltaX, (int)deltaY);
    }

    @Override
    public Point2D getPosition() {
        return this.position.getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }
}
