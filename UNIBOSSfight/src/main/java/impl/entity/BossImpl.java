package impl.entity;

import core.component.Hitbox;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Boss;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class BossImpl implements Boss {

    //Variables
    private Transform position;
    private int health;
    private Weapon weapon;

    public BossImpl(Transform startingPos, int health, Weapon weapon){
        this.position = startingPos;
        this.health = health;
        this.weapon = weapon;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void attack() {
        //TODO
    }

    public boolean isDead() {
        //TODO
        return  this.health <= 0; //HP below 0
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public int getDamage() {
        //TODO
        return this.getDamage();
    }

    public boolean isDisplayed() {
        return false;//TODO
    }

    public void render(GraphicsContext gc) {
        //TODO
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public Point2D getPosition() {
        return this.position.getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        return null;//TODO
    }
}
