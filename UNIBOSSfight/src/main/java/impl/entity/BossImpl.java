package impl.entity;

import core.component.Health;
import core.component.Transform;
import impl.component.TransformImpl;
import core.component.Weapon;
import core.entity.Boss;
import impl.component.HealthImpl;

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
