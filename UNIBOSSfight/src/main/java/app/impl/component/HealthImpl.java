package app.impl.component;

import app.core.component.Health;

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
     * Creates a new instance of the class HealthImpl with an initial hp value.
     */
    public HealthImpl(final int hp) {
        this.hp = hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return this.hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void damage(final int damage) {
        this.hp -= damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.hp = 0;
    }
}
