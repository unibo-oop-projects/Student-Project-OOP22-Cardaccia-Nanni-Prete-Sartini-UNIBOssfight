package app.impl.entity;

import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Boss;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.AnimationSpriteRenderer;
import app.impl.component.HealthImpl;
import app.impl.component.WeaponImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.geometry.Point2D;

/**
 * Implementation of the Boss Interface.
 */
public class BossImpl extends Boss {

    private static final int DEFAULT_RATE_OF_FIRE = 30;
    private static final int DEFAULT_MAX_X_SPEED = 5;
    private static final int DEFAULT_MAX_Y_SPEED = 20;
    private static final int DEFAULT_HEALTH = 400;
    private transient WeaponImpl weapon;
    private int rateOfFire;

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
        this.setMaxXSpeed(DEFAULT_MAX_X_SPEED);
        this.setMaxYSpeed(DEFAULT_MAX_Y_SPEED);

        this.rateOfFire = DEFAULT_RATE_OF_FIRE;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "This Class cannot be Cloned due to it being initialized through a Factory"
    )
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

        if (input == Inputs.EMPTY && this.getRenderer() instanceof AnimationSpriteRenderer) {
            if (this.getXSpeed() != 0) {
                ((AnimationSpriteRenderer) this.getRenderer()).setAnimation("walk");
            } else {
                ((AnimationSpriteRenderer) this.getRenderer()).setAnimation("idle");
            }
        }
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

        this.setHealth(new HealthImpl(DEFAULT_HEALTH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bullet shoot(final Point2D target) {
        if (this.getRenderer() instanceof AnimationSpriteRenderer) {
            ((AnimationSpriteRenderer) this.getRenderer()).setAnimation("attack");
        }

        final Bullet newBullet = this.weapon.fire(target);
        newBullet.init();
        return newBullet;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "This Class cannot be Cloned due to it being initialized through a Factory"
    )
    @Override
    public void setWeapon(final WeaponImpl weapon) {
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRateOfFire() {
        return this.rateOfFire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRateOfFire(final int rateOfFire) {
        this.rateOfFire = rateOfFire;
    }
}
