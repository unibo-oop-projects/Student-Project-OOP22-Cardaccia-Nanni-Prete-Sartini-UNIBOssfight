package app.core.component;

/**
 * This class models the health component
 * which determines if an entity is dead or not.
 */
public interface Health {

    /**
     * This method returns the current health value.
     *
     * @return the current health value
     */
    int getValue();

    /**
     * Sets the value of the health.
     *
     * @param hp the new health value
     */
    void setValue(int hp);

    /**
     * Subtracts the specified damage from the health.
     *
     * @param damage the damage received
     */
    void damage(int damage);

    /**
     * Determines if the entity is dead.
     *
     * @return if the entity is dead or not
     */
    boolean isDead();

    /**
     * Sets the health to zero, killing the entity.
     */
    void destroy();

}
