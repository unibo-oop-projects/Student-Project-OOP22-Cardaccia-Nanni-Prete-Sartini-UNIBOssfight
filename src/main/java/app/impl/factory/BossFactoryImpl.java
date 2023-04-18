package app.impl.factory;

import app.core.component.BossFactory;
import app.core.component.Transform;
import app.core.component.WeaponFactory;
import app.core.entity.Boss;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.HealthImpl;
import app.impl.entity.BossImpl;

/**
 * Implementation of the BossFactory Interface.
 */
public class BossFactoryImpl implements BossFactory {

    private final WeaponFactory weaponFactory = new WeaponFactoryImpl();
    private static final int METEOR_BOSS_HEIGHT = 500;
    private static final int METEOR_BOSS_WIDTH = 400;
    private static final int FLYING_BOSS_HEIGHT = 300;
    private static final int FLYING_BOSS_WIDTH = 150;
    private static final int FLYING_BOSS_RATEOFIRE = 49;
    private static final int FLYING_BOSS_MAX_SPEED = 5;
    private static final int FLYING_BOSS_MAX_HEALTH = 250;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss meteorBoss(final Transform startingPos) {

        final BossImpl boss = new BossImpl(startingPos, METEOR_BOSS_HEIGHT, METEOR_BOSS_WIDTH, "sistemiOperativi");
        boss.setWeapon(weaponFactory.getMeteorGun(startingPos, false));
        return boss;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss flyingBoss(final Transform startingPos) {

        final BossImpl boss = new BossImpl(startingPos, FLYING_BOSS_HEIGHT, FLYING_BOSS_WIDTH, "oop") {
            @Override
            public void init() {
                super.init();

                setBehaviour(new BehaviourBuilderImpl()
                        .addJumpOnTop()
                        .addStopFromBottom()
                        .addStopFromSide()
                        .addFlying()
                        .build());

                this.setRateOfFire(FLYING_BOSS_RATEOFIRE);
                this.setMaxXSpeed(FLYING_BOSS_MAX_SPEED);

                this.setHealth(new HealthImpl(FLYING_BOSS_MAX_HEALTH));
            }

            @Override
            protected boolean isJumping() {
                return false;
            }

            @Override
            protected void jump() {

            }
        };
        boss.setWeapon(weaponFactory.getBigBulletGun(startingPos, false));
        return boss;
    }

}
