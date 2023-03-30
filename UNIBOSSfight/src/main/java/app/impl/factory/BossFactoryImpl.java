package app.impl.factory;

import app.core.component.BossFactory;
import app.core.component.Transform;
import app.core.component.WeaponFactory;
import app.core.entity.Boss;
import app.impl.entity.BossImpl;

public class BossFactoryImpl implements BossFactory {

    WeaponFactory weaponFactory = new WeaponFactoryImpl();

    @Override
    public Boss firstBoss(final Transform startingPos){
        final Transform posCopy = startingPos.copyOf();
        posCopy.move(0, 250);
        return new BossImpl(startingPos, 500, 500, 1000, weaponFactory.getBigBulletGun(posCopy), 10, "testImage.png");
    }

}
