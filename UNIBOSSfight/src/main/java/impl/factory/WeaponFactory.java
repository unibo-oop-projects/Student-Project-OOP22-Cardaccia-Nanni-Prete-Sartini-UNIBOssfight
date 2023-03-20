package impl.factory;

import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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

            @Override
            public Bullet fire(final Point2D target){
                return bulletFactory.getBigBullet(userPos, target);
            }

        };
    }
}
