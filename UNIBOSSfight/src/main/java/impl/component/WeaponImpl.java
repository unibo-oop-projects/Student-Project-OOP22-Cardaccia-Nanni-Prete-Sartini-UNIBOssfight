package impl.component;

import core.component.Transform;
import core.component.Weapon;

public class WeaponImpl implements Weapon {

    private int damage;

    public WeaponImpl(int damage){

    }

    @Override
    public void update() {
        //TO DO
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void fire(Transform angle) {
        //TO DO
        //Maybe return bullet to draw
        //Find the trajectory to the target and create the bullet object
    }
}
