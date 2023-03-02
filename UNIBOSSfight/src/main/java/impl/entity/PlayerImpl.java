package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import core.entity.Bullet;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.Acceleration;
import util.Window;

import java.util.ArrayList;
import java.util.List;


public class PlayerImpl extends AbstractEntity {

    //TODO: aggiungere classe util HitBox

    //TODO: aggiungere classe util HitBoxprivate Hitbox playerHitbox;
    private int ySpeed = 0;

    private WeaponImpl weapon = new WeaponImpl(this.position, 10, new SpriteRenderer(150, 180, Color.RED, "gnu.png"));
    private double rotation;
    private List<Bullet> bullets = new ArrayList<>();
    private int cont = 1;


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
            this.renderer.render(gc, new Point2D(Window.getWidth() / 2, this.getPosition().getY()), this.getDirection());
            this.weapon.render(gc, this.getDirection());
        } catch (Exception e){
            System.out.println("ERROR cannot load resource " + e);
        }
    }

    @Override
    public ImageView render(Point2D position) {
        try{
            return this.renderer.render(new Point2D(Window.getWidth() / 2, this.getPosition().getY()-110), this.getDirection(), 0);
        } catch (Exception e){
            System.out.println("ERROR cannot load resource " + e);
        }

        return null;
    }
    public ImageView renderWeapon() {
        try{
            return this.weapon.render(this.getDirection(), (int)this.rotation);
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
                this.ySpeed = -20;
                this.position.move(0, -1);
            }
            if(this.cont++ % 3 == 0)
                shoot();

                //this.position.move(0, ySpeed);
            }
            case EMPTY -> {
                this.position.move(0, ySpeed);
                this.ySpeed = this.isJumping() ? Acceleration.accelerate(this.ySpeed, 20, 1) : 0;
                //System.out.println(this.bullets.stream().filter(e -> e.isDisplayed(this.getPosition())).count());
                this.bullets.forEach(e -> e.update(Inputs.EMPTY));
                for(int i = 0; i < this.bullets.size(); i++){
                    if(!this.bullets.get(i).isDisplayed(this.getPosition())){
                        this.bullets.remove(i);
                    }
                }
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
        this.rotation = this.direction * (mousePosition.getY() / Window.getHeight() * 120 - 55);
    }

    private void shoot(){
        this.bullets.add(this.weapon.fire(new Point2D(0, 100 )));
    }

    public List<ImageView> getBullets() {
        return this.bullets.stream().map(e -> e.render(getPosition())).toList();
    }
}
