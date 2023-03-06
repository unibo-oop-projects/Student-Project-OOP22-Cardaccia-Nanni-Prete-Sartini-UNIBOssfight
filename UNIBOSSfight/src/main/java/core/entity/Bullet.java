package core.entity;

import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import util.Window;

public abstract class Bullet extends AbstractEntity {

    private final int damage;
    private int speed;
    private final double xShift;
    private final double yShift;

    public Bullet(final Transform startingPos, final int height, final int width,
                  final Renderer renderer, final int damage, final Point2D target,
                  final int speed) {

        super(startingPos, height, width, renderer);

        //Bullet damage
        this.damage = damage;
        this.speed = speed;

        // Finding vector angle
        double dx = (target.getX() + getPosition().getX() - Window.getWidth() / 2)
                - getPosition().getX();
        double dy = (target.getY()) - getPosition().getY();
        double angle = Math.atan2(dy, dx);

        // Shifts on vector
        this.xShift = this.speed * Math.cos(angle);
        this.yShift = this.speed * Math.sin(angle);
    }

    @Override
    public void update(final Inputs input) {
        getTransform().move((int) xShift, (int) yShift);
    }

}
