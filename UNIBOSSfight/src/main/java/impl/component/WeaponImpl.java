package impl.component;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import core.entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public class WeaponImpl implements Weapon {

    private int damage;
    private Entity user;
    private Renderer renderer;

    public WeaponImpl(Entity user, int damage, Renderer renderer){
        //TODO
        this.user = user;
        this.damage = damage;
        this.renderer = renderer;
    }

    public void render(GraphicsContext gc){
        this.renderer.render(gc, this.user.getPosition().add(0, 0));
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public Bullet fire(Transform target) {
        //TODO
        //Maybe return bullet to draw
        //Find the trajectory to the target and create the bullet object
        return null;
    }
}
