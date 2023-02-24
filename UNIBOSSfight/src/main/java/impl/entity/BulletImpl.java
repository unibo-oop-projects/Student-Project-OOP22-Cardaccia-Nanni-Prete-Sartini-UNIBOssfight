package impl.entity;

import core.component.Hitbox;
import core.entity.Bullet;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2d;

public class BulletImpl implements Bullet {

    int damage;
    Vector2d position;
    Vector2d target;
    Vector2d trajectory;
    Hitbox hitbox;

    public BulletImpl(Vector2d startingPos, Vector2d target, int damage, Hitbox hitbox){
        this.position = startingPos;
        this.target = target;
        this.damage = damage;
        this.hitbox = hitbox;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean isDisplayed() {
        //TODO
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        //TODO
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }
}
