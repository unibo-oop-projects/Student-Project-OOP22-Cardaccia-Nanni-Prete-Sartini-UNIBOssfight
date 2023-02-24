package impl.component;

import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import core.entity.Entity;

public class WeaponImpl implements Weapon {

    private int damage;
    private Entity user;

    public WeaponImpl(Entity user, int damage){
        //TODO
        this.user = user;
        this.damage = damage;
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
