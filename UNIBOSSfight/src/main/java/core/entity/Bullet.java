package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Bullet extends AbstractEntity {
    private final double RIGHT_ANGLE = 90;
    private final int SPEED = 10;

    private int damage;
    private Point2D target;
    private  double angle;

    public Bullet(Transform startingPos, int height, int width, Renderer renderer, int damage, Point2D target){

        super(startingPos, height, width, renderer);

        this.damage = damage;
        this.target = target;
        this.angle = startingPos.getPosition().angle(target);
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

        deltaX = SPEED * Math.cos(oppositeAngleX);
        deltaY = SPEED * Math.cos(oppositeAngleY);

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
