package impl.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import core.entity.ActiveEntity;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class TmpEntityImpl extends ActiveEntity {
    private int health;
    private Hitbox hitbox;
    private final Integer height, width;

    public TmpEntityImpl(int health, Transform position, Integer height, Integer width) {
        super(health, position);
        this.height = height;
        this.width = width;
        this.renderer = new Renderer(this.height, this.width, Color.WHITE) {
            @Override
            public void update() {
                super.update();
            }
        };

        //TODO init hitbox
    }

    @Override
    public void setHealth(int health) {

    }

    public int getHealth() {
        return this.health;
    }

    public void attack() {

    }

    public boolean isDead() {
        return false;
    }

    @Override
    public void update() {

    }

    public Hitbox getHitbox() {
        return null;//TODO
    }
}
