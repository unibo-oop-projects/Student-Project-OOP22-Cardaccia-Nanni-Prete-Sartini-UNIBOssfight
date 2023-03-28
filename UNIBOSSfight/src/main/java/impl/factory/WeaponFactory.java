package impl.factory;

import core.component.Transform;
import impl.component.TransformImpl;
import core.component.Weapon;
import core.entity.Bullet;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class WeaponFactory {

    private final BulletFactory bulletFactory = new BulletFactory();

    public Weapon getPlayerWeapon(Transform playerPos){

        final Transform posCopy = playerPos.copyOf();
        //posCopy.move(90, -200);

        return new WeaponImpl(playerPos, posCopy, 50, new SpriteRenderer(150, 180, Color.RED, "gnu.png")){

            @Override
            public Bullet fire(final Point2D target){
                return bulletFactory.getPlayerBullet(this.getShootingPos(), target);
            }

        };
    }

    public Weapon getBigBulletGun(Transform userPos){

        final Transform posCopy = userPos.copyOf();
        posCopy.move(90, -200);

        return new WeaponImpl(userPos, posCopy, 100, new SpriteRenderer(150, 180, Color.RED, "gnu.png")){

            private final int RATE_OF_FIRE = 60;
            private int rateOfFireCounter = 0;

            @Override
            public Bullet fire(final Point2D target){
                this.rateOfFireCounter++;

                if(this.rateOfFireCounter >= RATE_OF_FIRE){
                    this.rateOfFireCounter = 0;
                    return bulletFactory.getBigBullet(userPos, target);
                }
                else
                {
                    return null;
                }
            }

        };
    }
}
