package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import util.Window;

public abstract class Bullet extends AbstractEntity {

    private final int damage;
    private int speed;
    private final double xShift;
    private final double yShift;

    public Bullet(Transform startingPos, int height, int width, Renderer renderer, int damage, Point2D target, int speed){

        super(startingPos, height, width, renderer);

        //Bullet damage
        this.damage = damage;
        this.speed = speed;

        //Finding vector angle
        double dx = (target.getX() + this.position.getPosition().getX() - Window.getWidth()/2)-this.position.getPosition().getX();
        double dy = (target.getY())-this.position.getPosition().getY();
        double angle = Math.atan2(dy, dx);

        //Shifs on vector
        this.xShift = this.speed * Math.cos(angle);
        this.yShift = this.speed * Math.sin(angle);
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
