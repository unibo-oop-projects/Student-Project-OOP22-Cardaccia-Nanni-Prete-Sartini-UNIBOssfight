package impl.entity;

import core.component.Collider;
import core.entity.Entity;
import impl.component.ColliderImpl;
import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Acceleration;

import java.util.Optional;


public class PlayerImpl extends AbstractEntity {

    //TODO: aggiungere classe util HitBox

    //TODO: aggiungere classe util HitBoxprivate Hitbox playerHitbox;
    private int ySpeed = 0;

    private WeaponImpl weapon = new WeaponImpl(this, 10, new SpriteRenderer(75, 90, Color.RED, "gnu.png"));


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
    public void update(Inputs input) {


        switch (input) {
            case LEFT -> {this.position.move(-5, 0); this.direction = -1;}
            case RIGHT -> {this.position.move(5, 0); this.direction = 1;}
            case SPACE -> { if(!isJumping()) {
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

        this.position.resetGroundLevel();
        this.hitbox.update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() < this.position.getGroundLevel();
    }

    @Override
    protected void initCollider() {
        var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.ENEMY, e -> {
            this.position.move(getIntersection(e), 0);
        });

        collider.addBehaviour(Collider.Entities.PLATFORM, e -> {
                // TODO comportamento in base alla direzione della collisione
                if (e.getPosition().getY() - getPosition().getY() > 0) {
                    final int topSide = (int) e.getPosition().getY() - e.getHeight();
                    this.position.setGroundLevel(topSide);
                    if (this.position.isUnderGroundLevel()) {
                        this.position.setGroundLevel();
                    }
                } else if (e.getPosition().getY() - getPosition().getY() < 0) {
                    this.position.moveTo((int) getPosition().getX(), (int)e.getPosition().getY() + getHeight() + 1);
                    this.ySpeed = 0;
                } else {
                    this.position.move(getIntersection(e), 0);
                }

        });
        this.collider = Optional.of(collider);
    }

    private int getIntersection(Entity e) {
        int side = (int)Math.signum(getPosition().getX() - e.getPosition().getX());

        int wallside = (int)e.getPosition().getX() + (e.getWidth() / 2 * side);
        int playerSide = (int)getPosition().getX() - (getWidth() / 2 * side);

        return wallside - playerSide;
    }
}
