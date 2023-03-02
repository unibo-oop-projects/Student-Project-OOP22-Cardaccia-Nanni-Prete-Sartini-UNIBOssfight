package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import util.Window;

public abstract class Bullet extends AbstractEntity {
    private final double ROUND_ANGLE = 360;
    private final int SPEED = 10;

    private final int damage;
    private   double angle;
    private final double xShift;
    private final double yShift;

    public Bullet(Transform startingPos, int height, int width, Renderer renderer, int damage, Point2D target){

        super(startingPos, height, width, renderer);

        //Bullet damage
        this.damage = damage;

        //Finding vector angle

        double dx = (target.getX() + this.position.getPosition().getX() - Window.getWidth()/2)-this.position.getPosition().getX();
        double dy = (target.getY())-this.position.getPosition().getY();
        double dir = Math.atan2(dy, dx);

        //Shifs on vector
        this.xShift = SPEED * Math.cos(dir);
        this.yShift = SPEED * Math.sin(dir);
    }

    @Override
    public void update(Inputs input) {
        this.position.move((int)xShift, (int)yShift);
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

}
