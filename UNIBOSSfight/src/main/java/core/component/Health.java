package core.component;

/**
 *
 */
public class Health implements Component {

    private int hp;

    public Health() {
        this.hp = 100;
    }


    /**
     * The current health value.
     * @return the current health value
     */
    public int getValue() {
        return this.hp;
    }

    public void damage(final int damage) {
        this.hp -= damage;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }
}
