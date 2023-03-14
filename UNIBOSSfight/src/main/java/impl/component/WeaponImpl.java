package impl.component;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import impl.entity.BulletImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import util.Window;

/**
 * This class implements the Weapon.
 */
public class WeaponImpl implements Weapon {

    private final int damage;
    private final Transform userPos;
    private final Renderer renderer;

    /**
     * Creates a new instance of the class Weapon.
     *
     * @param userPos the position of the entity
     *                which the weapon will be given to
     * @param damage the damage that the weapon can cause
     * @param renderer the renderer of the weapon
     */
    public WeaponImpl(final Transform userPos,
                      final int damage, final Renderer renderer) {
        this.userPos = userPos;
        this.damage = damage;
        this.renderer = renderer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node render(final int direction, final int rotation) {
        return this.renderer.render(new Point2D(Window.getWidth() / 2 + 10 * direction,
                this.userPos.getPosition().getY() + 80 - 110), direction, rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet fire(final Point2D target) {

        Transform shootingPosition = Transform.copyOf(this.userPos);
        shootingPosition.move(0, -125);

        return new BulletImpl(shootingPosition, 20, 20,
                new SpriteRenderer(20, 20, Color.BLACK, "testImage2.png"),
                1, target, 20);
    }
}

