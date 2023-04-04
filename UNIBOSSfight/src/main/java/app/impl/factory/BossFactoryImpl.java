package app.impl.factory;

import app.core.component.BossFactory;
import app.core.component.Transform;
import app.core.component.WeaponFactory;
import app.core.entity.Boss;
import app.impl.entity.BossImpl;

/**
 * Implementation of the BossFactory Interface.
 */
public class BossFactoryImpl implements BossFactory {

    private final WeaponFactory weaponFactory = new WeaponFactoryImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss firstBoss(final Transform startingPos) {

        final BossImpl boss = new BossImpl(startingPos, 500, 500, "ghini/ghini1.png");
        boss.setWeapon(weaponFactory.getGhiniGun(startingPos, false));

        return boss;
    }

}
