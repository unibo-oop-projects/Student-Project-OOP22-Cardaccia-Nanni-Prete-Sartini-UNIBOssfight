package core.entity;

import core.component.Hitbox;
import core.component.Transform;

public abstract class Enemy extends ActiveEntity {

    public Enemy(int health, Transform position) {
        super(health, position);
    }

    public abstract int getDamage();
}
