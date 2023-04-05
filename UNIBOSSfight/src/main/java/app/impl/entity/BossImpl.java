package app.impl.entity;

import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.component.WeaponFactory;
import app.core.entity.Boss;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.WeaponImpl;
import app.impl.factory.WeaponFactoryImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Implementation of the Boss Interface.
 */
public class BossImpl extends Boss {

    private static final int DEFAULT_RATE_OF_FIRE = 30;
    private transient WeaponImpl weapon;
    private final int rateOfFire;

    /**
     * Constructor that initializes a new instance of the Boss.
     *
     * @param startingPos The starting position of the Boss
     * @param height Height of the Boss sprite
     * @param width Width of the Boss sprite
     * @param filename Path of the Boss sprite
     */
    public BossImpl(final Transform startingPos, final int height, final int width, final String filename) {
        super(startingPos, height, width, filename);
        this.setMaxXSpeed(1);
        this.setMaxYSpeed(1);

        this.rateOfFire = DEFAULT_RATE_OF_FIRE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {
        super.update(input);
        this.weapon.updatePosition(getTransform());
        this.weapon.setXDirection(this.getDirection());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        setBehaviour(new BehaviourBuilderImpl()
                .addJumpOnTop()
                .addStopFromBottom()
                .addStopFromSide()
                .addFollow()
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet shoot(final Point2D target) {
        final Bullet newBullet = this.weapon.fire(target);
        newBullet.init();
        return newBullet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWeapon(final WeaponImpl weapon) {
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node renderWeapon(final Point2D playerPosition) {
        return this.weapon.render(playerPosition, 0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRateOfFire() {
        return this.rateOfFire;
    }
}
