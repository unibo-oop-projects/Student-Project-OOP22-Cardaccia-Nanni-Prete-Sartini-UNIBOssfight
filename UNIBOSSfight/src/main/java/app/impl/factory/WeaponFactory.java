package app.impl.factory;

import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Bullet;
import app.impl.component.SpriteRenderer;
import app.impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class WeaponFactory {

    private final BulletFactory bulletFactory = new BulletFactory();

    public Weapon getPlayerWeapon(Transform playerPos){

        return new WeaponImpl(playerPos, 50, new SpriteRenderer(150, 180, Color.RED, "gnu.png")){

            @Override
            public Bullet fire(final Point2D target){
                return bulletFactory.getPlayerBullet(playerPos, target);
            }

        };
    }

    public Weapon getBigBulletGun(Transform userPos){
        return new WeaponImpl(userPos, 100, new SpriteRenderer(150, 180, Color.RED, "gnu.png")){

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
