package core.entity;

import core.component.Transform;
import core.component.Weapon;

public abstract class Boss extends Enemy {

    public Boss(int health, Transform position) {
        super(health, position);
    }

    Weapon getWeapon(){
        return null;
    }

}
