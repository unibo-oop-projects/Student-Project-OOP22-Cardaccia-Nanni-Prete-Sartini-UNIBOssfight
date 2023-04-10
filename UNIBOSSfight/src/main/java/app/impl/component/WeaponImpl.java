package app.impl.component;

import app.core.component.BulletFactory;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.impl.entity.Bullet;
import app.impl.factory.BulletFactoryImpl;
import app.util.Angle;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private int xDirection = 1;
    private double rotation;

    /**
     * Creates a new instance of the class Weapon.
     *
     * @param userPos the position of the entity
     *                which the weapon will be given to
     * @param renderer the renderer of the weapon
     * @param positionOffset the offset from the user's position and the render position of the Weapon
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "The renderer Class cannot be Cloned"
    )
    public WeaponImpl(final Transform userPos, final Renderer renderer, final int positionOffset) {
        this.userPos = new TransformImpl(userPos.getPosition(), 0);
        this.renderer = renderer;
        this.renderer.init();
        this.shootingPos = new TransformImpl(userPos.getPosition(), 0);
        this.positionOffset = positionOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node render(final Point2D playerPosition, final int direction, final int rotation) {
        return this.renderer.render(new Point2D(
                this.getWeaponPosition().getPosition()
                        .subtract(playerPosition)
                        .add(Window.getWidth() / 2, 0)
                        .getX(),
                this.getRenderPosition().getPosition().getY()), this.xDirection, this.yDirection, this.rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet fire(final Point2D target) {
        return this.bulletFactory.getPlayerBullet(this.getWeaponPosition(), target, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setYDirection(final int yDirection) {
        this.yDirection = yDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXDirection(final int xDirection) {
        this.xDirection = xDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePosition(final Transform newPos) {
        final Transform posCopy = newPos.copyOf();

        this.userPos.moveTo(posCopy.getPosition().getX(), posCopy.getPosition().getY());
        this.shootingPos.moveTo(getWeaponPosition().getPosition().getX(), getWeaponPosition().getPosition().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getWeaponPosition() {
        final Transform posCopy = getRenderPosition().copyOf();
        posCopy.move(0, renderer.getHeight() / 2.0);
        return posCopy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getUserPosition() {
        return this.userPos.copyOf();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getShootingPosition() {
        return this.shootingPos.copyOf();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getRenderPosition() {
        final Transform posCopy = this.userPos.copyOf();
        posCopy.move(0, positionOffset);
        return posCopy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double updateRotation(final Point2D target) {
        this.rotation = Math.toDegrees(Angle.findAngle(this.getShootingPosition().getPosition(), target));
        if (this.rotation <= Angle.RIGHT_ANGLE && this.rotation > -Angle.RIGHT_ANGLE) {
            setYDirection(1);
        } else {
            setYDirection(-1);
        }
        return this.rotation;
    }

}

