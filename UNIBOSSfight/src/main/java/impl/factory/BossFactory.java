package impl.factory;

import core.component.Transform;
import core.entity.Boss;
import impl.component.TransformImpl;
import impl.entity.BossImpl;

public class BossFactory {

    WeaponFactory weaponFactory = new WeaponFactory();

    public Boss firstBoss(Transform startingPos){
        Transform posCopy = startingPos.copyOf();
        posCopy.move(0, -250);
        return new BossImpl(startingPos, 500, 500, 1000, weaponFactory.getBigBulletGun(posCopy), "goomba.png");
    }

}
