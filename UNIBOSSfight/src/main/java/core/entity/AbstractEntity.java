package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import util.Window;

import java.util.Optional;

public abstract class AbstractEntity implements Entity {

    private final int height;
    private final int width;
    private Optional<Integer> damage;

    protected Transform position;
    protected Hitbox hitbox;
    protected Renderer renderer;

    protected int direction = 1;

    public AbstractEntity(final Transform position, final int height,
                          final int width, final Renderer renderer) {
        this.position = position;
        this.renderer = renderer;
        this.height = height;
        this.width = width;

        this.hitbox = new Hitbox(width / 2.0, height, getPosition()) {
            @Override
            public void update() {

            }
        };
    }

    public abstract void update(Inputs input);

    public Point2D getPosition() {
        return this.position.getPosition();
    }

    public void render(GraphicsContext gc, Point2D position) {
        this.renderer.render(gc, new Point2D(this.getPosition().subtract(position).add(300, 0).getX(), this.getPosition().getY()), this.getDirection());
    }

    @Override
    public ImageView render(Point2D position) {
        return this.renderer.render(
                new Point2D(
                        this.getPosition().subtract(position)
                                .add(Window.getWidth() / 2, 0).getX(),
                        this.getPosition().getY() - 190
                ),
                this.getDirection(),
                0
        );
    }

    @Override
    public boolean isDisplayed(Point2D playerPosition) {
        System.out.println(this.getClass() + " " + this.getPosition().getY() + " " + Window.getHeight());
        return Math.abs(this.getPosition().subtract(playerPosition).getX()) < Window.getWidth() / 2 &&
                this.getPosition().getY() <= Window.getHeight() &&
                this.getPosition().getY() > 0;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    protected int getDirection() {
        return this.direction;
    }

    public Optional<Integer> getDamage() {
        return this.damage;
    }

    protected void setDamage(Optional<Integer> damage) {
        this.damage = damage;
    }


}
