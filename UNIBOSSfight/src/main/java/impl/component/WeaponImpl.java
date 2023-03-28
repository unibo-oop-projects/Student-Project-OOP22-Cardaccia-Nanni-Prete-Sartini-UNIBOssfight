package impl.component;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import impl.factory.BulletFactory;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import util.Window;

/**
 * This class implements the Weapon.
 */
public class WeaponImpl implements Weapon {

    private final int damage;
    private final Transform userPos;
    private Transform shootingPos;
    private final Renderer renderer;
    private final BulletFactory bulletFactory = new BulletFactory();
    private int yDirection = 1;

    /**
     * Creates a new instance of the class Weapon.
     *
     * @param userPos the position of the entity
     *                which the weapon will be given to
     * @param damage the damage that the weapon can cause
     * @param renderer the renderer of the weapon
     */
    public WeaponImpl(final Transform userPos, final Transform shootingPos,
                      final int damage, final Renderer renderer) {
        this.userPos = userPos;
        this.damage = damage;
        this.renderer = renderer;
        this.shootingPos = shootingPos;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node render(final int direction, final int rotation) {
        return this.renderer.render(new Point2D(Window.getWidth() / 2 + 10,
                this.userPos.getPosition().getY() -30), 1, this.yDirection, rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet fire(final Point2D target) {
        Bullet tempBullet = this.bulletFactory.getPlayerBullet(this.getShootingPos(), target);
        return tempBullet;
    }

    @Override
    public Transform getShootingPos(){ return this.shootingPos; }

    @Override
    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    @Override
    public void updatePosition(Point2D newPos) {
        this.userPos.moveTo(newPos.getX(), newPos.getY());
        this.shootingPos.moveTo(this.userPos.getPosition().getX() + 90, this.userPos.getPosition().getY() - 125);
    }
}

