package app.impl.component;

import app.core.component.Health;

/**
 * This class implements the Health.
 */
public class HealthImpl implements Health {

    private static final int DEFAULT_HP = 100;

    private final int maxHp;
    private int hp;

    /**
     * Creates a new instance of the class HealthImpl.
     */
    public HealthImpl() {
        this(DEFAULT_HP);
    }

    /**
     * Creates a new instance of the class HealthImpl given an initial
     * health points value.
     * @param hp initial health points
     */
    public HealthImpl(final int hp) {
        this.maxHp = hp;
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
