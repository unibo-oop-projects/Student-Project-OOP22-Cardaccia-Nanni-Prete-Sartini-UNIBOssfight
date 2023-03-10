package core.component;

/**
 * This class models the health component which determines if an entity is dead or not.
 */
public class Health implements Component {

    private int hp;

    /**
     * Creates a new instance of health.
     */
    public Health() {
        this.hp = 100;
    }

    /**
     * @return the current health value
     */
    public int getValue() {
        return this.hp;
    }

    /**
     * Subtracts the specified damage from the health.
     * @param damage the damage received
     */
    public void damage(final int damage) {
        this.hp -= damage;
    }

    /**
     * Determines if the entity is dead.
     * @return if the entity is dead or not
     */
    public boolean isDead() {
        return this.hp <= 0;
    }
}
