package core.entity;

import core.component.Hitbox;
import core.component.Transform;

public abstract class Enemy extends ActiveEntity {

    public Enemy(int health, Transform position) {
        super(health, position);
    }

    @Override
    public void update() {

    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }
}
