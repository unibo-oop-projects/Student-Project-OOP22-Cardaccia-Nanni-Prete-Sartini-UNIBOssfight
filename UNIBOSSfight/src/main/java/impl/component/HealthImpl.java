package impl.component;

import core.component.Health;

/**
 * This class implements the Health.
 */
public class HealthImpl implements Health {

    private int hp;

    /**
     * Creates a new instance of the class HealthImpl.
     */
    public HealthImpl() {
        this.hp = 100;
    }

    /**
     * {@inheritDoc}
     */
    public int getValue() {
        return this.hp;
    }

    /**
     * {@inheritDoc}
     */
    public void damage(final int damage) {
        this.hp -= damage;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDead() {
        return this.hp <= 0;
    }
}
