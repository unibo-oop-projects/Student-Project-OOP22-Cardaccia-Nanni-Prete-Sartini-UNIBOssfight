package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.entity.AbstractEntity;
import core.entity.Entity;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.Acceleration;


public class PlayerImpl extends AbstractEntity {

    //TODO: aggiungere classe util HitBox

    //TODO: aggiungere classe util HitBoxprivate Hitbox playerHitbox;
    private int ySpeed = 0;

    private WeaponImpl weapon = new WeaponImpl(this.position, 10, new SpriteRenderer(150, 180, Color.RED, "gnu.png"));


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
            this.renderer.render(gc, new Point2D(300, this.getPosition().getY()), this.getDirection());
            this.weapon.render(gc, this.getDirection());
        } catch (Exception e){
            System.out.println("ERROR cannot load resource " + e);
        }
    }

    @Override
    public ImageView render(Point2D position) {
        try{
            return this.renderer.render(new Point2D(300, this.getPosition().getY()), this.getDirection());
            //this.weapon.render(this.getDirection());
        } catch (Exception e){
            System.out.println("ERROR cannot load resource " + e);
        }

        return null;
    }

    @Override
    public void update(Inputs input) {


        switch (input) {
            case LEFT -> {this.position.move(-5, 0); this.direction = -1;}
            case RIGHT -> {this.position.move(5, 0); this.direction = 1;}
            case SPACE -> { if(!isJumping()) {
                    System.out.println("salto");
                    this.ySpeed = -20;
                    this.position.move(0, -1);

                }
                //this.position.move(0, ySpeed);
            }
            case EMPTY -> {
                this.position.move(0, ySpeed);
                this.ySpeed = this.isJumping() ? Acceleration.accelerate(this.ySpeed, 20, 1) : 0;
            }
        }

        //this.position.move(0, ySpeed);

        this.position.setGroundLevel();
        this.hitbox.update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() < 599;
    }

    public void rotateWeapon(Point2D mousePosition) {
        this.weapon.rotation = this.getPosition().angle(mousePosition);
    }
}
