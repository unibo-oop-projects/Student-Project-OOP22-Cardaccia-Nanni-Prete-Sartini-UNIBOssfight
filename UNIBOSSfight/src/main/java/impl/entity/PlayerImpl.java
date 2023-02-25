package impl.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import core.entity.ActiveEntity;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class PlayerImpl extends ActiveEntity {

    //TODO: aggiungere classe util HitBox
    private final Integer height, width;
    private Hitbox playerHitbox;



    public PlayerImpl(int health, Transform position, Integer height, Integer width) {
        super(health, position);

        this.height = height;
        this.width = width;
        this.renderer = new Renderer(this.height, this.width, Color.GREEN) {
            @Override
            public void update() {
                super.update();
            }
        };
    }

    @Override
    public void setHealth(int health) {

    }

    @Override
    public boolean isDisplayed(Point2D position) {
        return true;
    }

    @Override
    public void render(GraphicsContext gc, Point2D position) {
        this.renderer.render(gc, new Point2D(300, 300));
    }

    @Override
    public void update() {

    }

    @Override
    public Hitbox getHitbox() {
        return this.playerHitbox;
    }
}
