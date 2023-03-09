package core.component;

/**
 *
 */
public class Health implements Component {

    private int hp;

    public Health() {
        this.hp = 100;
    }

    public int getHealth() {
        return this.hp;
    }

    public void damage(final int damage) {
        this.hp -= damage;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }
}
