package impl.entity;

import core.component.Collider;
import core.entity.Bullet;
import core.entity.Entity;
import impl.component.ColliderImpl;
import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import util.Acceleration;
import util.Window;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


public class PlayerImpl extends AbstractEntity {

    //TODO: aggiungere classe util HitBox

    //TODO: aggiungere classe util HitBoxprivate Hitbox playerHitbox;
    private int ySpeed = 0;

    private WeaponImpl weapon = new WeaponImpl(this.position, 10, new SpriteRenderer(75, 90, Color.RED, "gnu.png"));
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
    public Node render(Point2D position) {
        try{
            return this.renderer.render(new Point2D(Window.getWidth() / 2, this.getPosition().getY() - 57), this.getDirection(), 0);
        } catch (Exception e){
            System.out.println("ERROR cannot load resource " + e);
        }
        return null;
    }

    public Node renderWeapon() {
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
            case LEFT -> {
                this.position.move(-5, 0);
                this.direction = -1;
            }
            case RIGHT -> {
                this.position.move(5, 0);
                this.direction = 1;
            }
            case SPACE -> {
                if (!isJumping()) {
                    this.ySpeed = -20;
                    this.position.move(0, -1);
                }
                if (this.cont++ % 3 == 0) {
                    shoot();
                    System.out.println(this.bullets.size());
                }

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

        this.position.resetGroundLevel();
        this.hitbox.update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() < this.position.getGroundLevel();
    }

    @Override
    protected void initCollider() {
        var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.TMPENTITY, e -> {
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

        int wallSide = (int)e.getPosition().getX() + (e.getWidth() / 2 * side);
        int playerSide = (int)getPosition().getX() - (getWidth() / 2 * side);

        return wallSide - playerSide;
    }

    public void rotateWeapon(Point2D mousePosition) {
        this.rotation = this.direction * (mousePosition.getY() / Window.getHeight() * 120 - 55);
    }

    private void shoot() {
        this.bullets.add(this.weapon.fire(new Point2D(0, 100 )));
    }

    public List<Node> getBullets() {
        return this.bullets.stream().map(e -> e.render(getPosition())).toList();
    }
}
