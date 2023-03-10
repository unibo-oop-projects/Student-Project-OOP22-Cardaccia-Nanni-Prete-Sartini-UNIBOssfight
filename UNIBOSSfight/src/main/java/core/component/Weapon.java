package core.component;

import core.entity.Bullet;
import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * This class models the weapon component, which is used by an entity
 * to inflict damage towards other entities by firing bullets.
 */
public interface Weapon extends Component {

    /**
     * Renders the weapon.
     * @param direction the direction of the weapon
     * @param rotation the rotation of the weapon
     * @return the Node that will be given as input to the Scene,
     *         representing the rendered weapon
     */
    Node render(int direction, int rotation);

    /**
     * Used to create new bullets shot by the weapon towards a certain target.
     * @param target the target at which the weapon is pointing
     * @return the bullet fired
     */
    Bullet fire(Point2D target);

}
