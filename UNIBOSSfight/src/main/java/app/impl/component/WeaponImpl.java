package app.impl.component;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Bullet;
import app.impl.factory.BulletFactory;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import app.util.Window;

/**
 * This class implements the Weapon.
 */
public class WeaponImpl implements Weapon {

    private final int positionOffset = 0;
    private final int damage;
    private final Transform userPos;
    private final Transform shootingPos;
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
    public WeaponImpl(final Transform userPos,
                      final int damage, final Renderer renderer) {
        this.userPos = new TransformImpl(userPos.getPosition(), 0);
        this.damage = damage;
        this.renderer = renderer;
        this.shootingPos = getWeaponPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node render(final int direction, final int rotation) {
        return this.renderer.render(new Point2D(Window.getWidth() / 2,
                this.getUserPosition().getPosition().getY()), 1, this.yDirection, rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet fire(final Point2D target) {
        Bullet tempBullet = this.bulletFactory.getPlayerBullet(this.getWeaponPosition(), target);
        return tempBullet;
    }

    @Override
    public Transform getShootingPos(){ return this.shootingPos; }

    @Override
    public void setYDirection(final int yDirection) {
        this.yDirection = yDirection;
    }

    @Override
    public void updatePosition(final Transform newPos) {
        Transform posCopy = newPos.copyOf();

        this.userPos.moveTo(posCopy.getPosition().getX(), posCopy.getPosition().getY());
        this.shootingPos.moveTo(this.getWeaponPosition().getPosition().getX(), this.getWeaponPosition().getPosition().getY());
    }

    @Override
    public Transform getWeaponPosition() {
        Transform posCopy = this.userPos.copyOf();
        posCopy.move(0, positionOffset);
        return posCopy;
    }

    @Override
    public Transform getUserPosition() {
        return this.userPos;
    }

    @Override
    public Transform getShootingPosition() {
        return this.shootingPos;
    }
}

