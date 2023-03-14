package core.component;

/**
 * This class models the health component which determines if an entity is dead or not.
 */
public interface Health extends Component {

    /**
     * @return the current health value
     */
    int getValue();

    /**
     * Subtracts the specified damage from the health.
     * @param damage the damage received
     */
    void damage(int damage);

    /**
     * Determines if the entity is dead.
     * @return if the entity is dead or not
     */
    boolean isDead();

}
