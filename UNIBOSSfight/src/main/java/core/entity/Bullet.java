package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Bullet extends AbstractEntity {
    private final double ROUND_ANGLE = 360;
    private final int SPEED = 10;

    private final int damage;
    private final  double angle;
    private final double xShift;
    private final double yShift;

    public Bullet(Transform startingPos, int height, int width, Renderer renderer, int damage, Point2D target){

        super(startingPos, height, width, renderer);

        //Bullet damage
        this.damage = damage;

        //Finding vector angle
        this.angle = Math.toDegrees(Math.atan2(target.getX() - startingPos.getPosition().getX(), target.getY() - startingPos.getPosition().getY()));

        //Shifs on vector
        this.xShift = SPEED * Math.cos(angle);
        this.yShift = SPEED * Math.sin(angle);
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
        this.position.move((int)xShift, (int)yShift);
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
