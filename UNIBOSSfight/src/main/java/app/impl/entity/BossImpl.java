package app.impl.entity;

import app.core.component.Health;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Boss;
import app.impl.component.HealthImpl;

public class BossImpl extends Boss {

    private Weapon weapon;
    private Health health;

    public BossImpl(final Transform startingPos, final int height, final int width,
                    final int health, final Weapon weapon, final String filename) {
        super(startingPos, height, width, filename);

        this.weapon = weapon;
        this.health = new HealthImpl();

        //TODO health
    }

    public boolean isDead() {
        return  this.health.isDead();
    }

    @Override
    public Weapon getWeapon() { return this.weapon; }

    @Override
    public void update(final Inputs input) {

        getTransform().move(getDirection(), 0);
        getHitbox().update(getTransform().getPosition());
        //TODO

    }

}
