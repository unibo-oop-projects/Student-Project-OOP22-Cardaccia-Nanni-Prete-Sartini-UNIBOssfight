package app.impl.factory;

import app.core.component.BossFactory;
import app.core.component.Transform;
import app.core.component.WeaponFactory;
import app.core.entity.Boss;
import app.impl.entity.BossImpl;

public class BossFactoryImpl implements BossFactory {

    WeaponFactory weaponFactory = new WeaponFactoryImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss firstBoss(final Transform startingPos){

        final BossImpl boss = new BossImpl(startingPos, 500, 500, "ghini/ghini1.png");
        boss.setWeapon(weaponFactory.getBigBulletGun(startingPos));

        return boss;
    }

}
