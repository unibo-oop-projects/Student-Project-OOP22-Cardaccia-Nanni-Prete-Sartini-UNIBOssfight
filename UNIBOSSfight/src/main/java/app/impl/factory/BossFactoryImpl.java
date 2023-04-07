package app.impl.factory;

import app.core.component.BossFactory;
import app.core.component.Transform;
import app.core.component.WeaponFactory;
import app.core.entity.Boss;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.entity.BossImpl;

/**
 * Implementation of the BossFactory Interface.
 */
public class BossFactoryImpl implements BossFactory {

    private final WeaponFactory weaponFactory = new WeaponFactoryImpl();
    private static final int FIRST_BOSS_HEIGHT = 500;
    private static final int FIRST_BOSS_WIDTH = 400;
    private static final int FLYING_BOSS_HEIGHT = 300;
    private static final int FLYING_BOSS_WIDTH = 150;
    private static final int FLYING_BOSS_RATEOFIRE = 49;
    private static final int FLYING_BOSS_MAX_SPEED = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss meteorBoss(final Transform startingPos) {

        final BossImpl boss = new BossImpl(startingPos, FIRST_BOSS_HEIGHT, FIRST_BOSS_WIDTH, "ghini");
        boss.setWeapon(weaponFactory.getMeteorGun(startingPos, false));

        return boss;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss flyingBoss(final Transform startingPos) {

        final BossImpl boss = new BossImpl(startingPos, FLYING_BOSS_HEIGHT, FLYING_BOSS_WIDTH, "pianini") {
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
            }

            @Override
            protected boolean isJumping() {
                return false;
            }
        };
        boss.setWeapon(weaponFactory.getBigBulletGun(startingPos, false));
        return boss;
    }

}
