package impl.entity;

import core.component.Transform;
import core.component.Weapon;
import core.entity.Boss;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class BossImpl extends Boss {

    //Variables
    private final Transform position;
    private final int health;
    private final Weapon weapon;

    public BossImpl(final Transform startingPos, final int height, final int width,
                    final int health, final Weapon weapon, final String filename) {
        super(startingPos, height, width, filename);
        this.position = Transform.copyOf(startingPos);
        this.health = health;
        this.weapon = weapon;
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

    public boolean isDisplayed() {
        return false; //TODO
    }

    public void render(final GraphicsContext gc) {
        //TODO
    }

    @Override
    public void update(final Inputs input) {
        //TODO
    }

    @Override
    public Point2D getPosition() {
        return this.position.getPosition();
    }

}
