package app.impl.component;

import app.core.component.BulletFactory;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Bullet;
import app.impl.factory.BulletFactoryImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import app.util.Window;

/**
 * This class implements the Weapon.
 */
public class WeaponImpl implements Weapon {

    private final int positionOffset;
    private final Transform userPos;
    private final Transform shootingPos;
    private final Renderer renderer;
    private final BulletFactory bulletFactory = new BulletFactoryImpl();
    private int yDirection = 1;
    private int rotation = 0;

    /**
     * Creates a new instance of the class Weapon.
     *
     * @param userPos the position of the entity
     *                which the weapon will be given to
     * @param damage the damage that the weapon can cause
     * @param renderer the renderer of the weapon
     */
    public WeaponImpl(final Transform userPos,
                      final int damage, final Renderer renderer, int positionOffset) {
        this.userPos = new TransformImpl(userPos.getPosition(), 0);
        this.renderer = renderer;
        this.renderer.init();
        this.shootingPos = getWeaponPosition();
        this.positionOffset = positionOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node render(final Point2D playerPosition, final int direction, final int rotation) {
        this.rotation = rotation;
        return this.renderer.render(new Point2D(
                this.getRenderPosition().getPosition()
                        .subtract(playerPosition)
                        .add(Window.getWidth() / 2, 0)
                        .getX(),
                this.getRenderPosition().getPosition().getY()), 1, this.yDirection, rotation);
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
    public void setYDirection(final int yDirection) {
        this.yDirection = yDirection;
    }

    @Override
    public void updatePosition(final Transform newPos) {
        Transform posCopy = newPos.copyOf();

        //TODO PULIRE CODICE
        this.userPos.moveTo(posCopy.getPosition().getX(), posCopy.getPosition().getY());
        //this.shootingPos.moveTo();
        this.shootingPos.moveTo(getWeaponPosition().getPosition().getX(), getWeaponPosition().getPosition().getY());




        //System.out.println(Math.toRadians(this.rotation));
        //this.shootingPos.move(-1 * Math.sin(Math.toRadians(rotation - 90)) * (this.renderer.getWidth()/2), -1 * Math.sin(Math.toRadians(rotation) * (this.renderer.getWidth()/2)) );
        //this.shootingPos.move(, );
    }

    @Override
    public Transform getWeaponPosition() {
        Transform posCopy = getRenderPosition().copyOf();
        posCopy.move(0, renderer.getHeight() / 2);
        return posCopy;
    }

    @Override
    public Transform getUserPosition() {
        return this.userPos;
    }

    @Override
    public Transform getShootingPosition() { return this.shootingPos; }

    @Override
    public Transform getRenderPosition() {
        Transform posCopy = this.userPos.copyOf();
        posCopy.move(0, positionOffset);
        return posCopy;
    }
}

