package impl.entity;

import core.component.Component;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Boss;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

import javax.sound.midi.Track;
import javax.xml.crypto.dsig.TransformService;

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

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void attack() {
        //TO DO
    }

    @Override
    public boolean isDead() {
        //TO REWORK
        return  this.health <= 0; //HP below 0
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public int getDamage() {
        //TO REWORK
        return this.weapon.getDamage();
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void update() {
        //TO DO
    }

    @Override
    public Vector2d getPosition() {
        return this.position.getPosition();
    }
}
