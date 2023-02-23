package impl.entity;

import core.component.Renderer;
import core.component.Transform;
import core.entity.ActiveEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Vector2d;



public class TmpEntityImpl implements ActiveEntity {
    private int health;

    private Transform position;
    private Renderer renderer;

    //TODO: aggiungere classe util HitBox
    private final Integer height, width;



    public TmpEntityImpl(int health, Transform position, Integer height, Integer width) {
        this.health = health;
        this.position = position;
        this.height = height;
        this.width = width;
        this.renderer = new Renderer(this.height, this.width, Color.WHITE) {
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
    public int getHealth() {
        return this.health;
    }

    @Override
    public void attack() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isDisplayed() {
        return true;
    }

    @Override
    public void render(GraphicsContext gc) {
        this.renderer.render(gc, this.getPosition());
    }

    @Override
    public void update() {

    }

    public void update(PlayerImpl.Inputs input) {
        switch (input) {
            case LEFT -> this.position.move(-5, 0);
            case RIGHT -> this.position.move(5, 0);
            case SPACE -> this.position.move(0, -5);
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position.getPosition();
    }
}
