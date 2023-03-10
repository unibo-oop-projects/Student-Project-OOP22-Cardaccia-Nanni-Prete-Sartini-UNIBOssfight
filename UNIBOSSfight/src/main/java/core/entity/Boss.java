package core.entity;

import core.component.Transform;
import core.component.Weapon;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

/**
 * This class models a more specific type of enemy, the boss,
 * that can cause damage by using a weapon.
 */
public abstract class Boss extends Enemy {

    /**
     * Creates a new instance of the class Boss.
     * @param position the position of the boss
     * @param height the height of the boss
     * @param width the width of the boss
     * @param filename the name of the file containing
     *                 the sprite of the boss
     */
    public Boss(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width, new SpriteRenderer(height, width, Color.RED, filename));
    }

    /**
     * @return the weapon of the boss
     */
    public Weapon getWeapon() {
        return null;
    }

}
