package app.impl.component;

import app.core.component.Health;

/**
 * This class implements the Health.
 */
public class HealthImpl implements Health {

    private final int maxHp;
    private int hp;

    /**
     * Creates a new instance of the class HealthImpl.
     */
    public HealthImpl() {
        this.maxHp = 100;
        this.hp = 100;
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
    public int getMaxValue() {
        return this.maxHp;
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
