package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.entity.AbstractEntity;
import core.entity.Entity;
import impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Acceleration;


public class PlayerImpl extends AbstractEntity {

    //TODO: aggiungere classe util HitBox

    //TODO: aggiungere classe util HitBoxprivate Hitbox playerHitbox;
    private int ySpeed = 0;
    private Hitbox playerHitbox;


    public PlayerImpl(Transform position, Integer height, Integer width, String filename) {
        super(position, height, width, new SpriteRenderer(height, width, Color.RED, filename));
    }
    @Override
    public boolean isDisplayed(Point2D position) {
        return true;
    }


    @Override
    public void render(GraphicsContext gc, Point2D position) {
        try{
            this.renderer.render(gc, new Point2D(300, this.getPosition().getY()));
        } catch (Exception e){
            System.out.println("ERROR cannot load resource " + e);
        }
    }

    @Override
    public void update(Inputs input) {


        switch (input) {
            case LEFT -> this.position.move(-5, 0);
            case RIGHT -> this.position.move(5, 0);
            case SPACE -> { if(!isJumping()) {
                    System.out.println("salto");
                    this.ySpeed = -20;
                    this.position.move(0, -1);

                }
                //this.position.move(0, ySpeed);
            }
            case EMPTY -> {
                this.position.move(0, ySpeed);
                this.ySpeed = this.isJumping() ? Acceleration.accellerate(this.ySpeed, 20, 1) : 0;
            }
        }

        //this.position.move(0, ySpeed);

        this.position.setGroundLevel();
    }

    @Override
    public Hitbox getHitbox() {
        return this.playerHitbox;
    }

    private boolean isJumping() {
        return this.getPosition().getY() < 599;
    }
}
