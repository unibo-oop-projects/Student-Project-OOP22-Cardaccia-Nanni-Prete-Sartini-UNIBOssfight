package app.core.entity;

import app.core.component.Transform;
import app.core.component.Weapon;
import app.impl.component.SpriteRenderer;
import app.impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * This class models a more specific type of enemy, the boss,
 * that can cause damage by using a weapon.
 */
public abstract class Boss extends Enemy {

    /**
     * Creates a new instance of the class Boss.
     *
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
     * This method returns the weapon of the boss.
     *
     * @return the weapon of the boss
     */
    public abstract Weapon getWeapon();

    /**
     * Method that makes the Boss shoot.
     *
     * @param target target to be shot
     */
    public abstract void shoot(Point2D target);

    /**
     * Method that returns the list of the Bullets Nodes to be rendered.
     *
     * @return the list of the Bullets Nodes
     */
    public abstract List<Node> getBulletsNodes();

    /**
     * Method to set the Bosses Weapon.
     *
     * @param weapon the new Weapon
     */
    public abstract void setWeapon(WeaponImpl weapon);

    /**
     * The Node of the Bosses Weapon to be rendered.
     *
     * @return the Weapon's Node
     */
    public abstract Node renderWeapon(Point2D playerPosition);
}
