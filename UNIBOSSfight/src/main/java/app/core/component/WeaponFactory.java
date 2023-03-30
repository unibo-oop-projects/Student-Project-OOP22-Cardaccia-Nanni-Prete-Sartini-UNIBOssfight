package app.core.component;

public interface WeaponFactory {

    Weapon getPlayerWeapon(final Transform playerPos);

    Weapon getBigBulletGun(final Transform userPos);

}
