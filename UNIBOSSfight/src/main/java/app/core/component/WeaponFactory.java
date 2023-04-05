package app.core.component;

import app.impl.component.WeaponImpl;

/**
 * An interface modelling a Factory to create instances of Weapons.
 */
public interface WeaponFactory {

    /**
     * Returns an instance of the player weapon.
     *
     * @param playerPos the player's position
     * @return an instance of the player weapon
     */
    WeaponImpl getPlayerWeapon(Transform playerPos, boolean isPlayerWeapon);

    /**
     * Returns an instance of the bigBullet weapon.
     *
     * @param userPos position of the weapon's user
     * @return an instance of the bigBullet weapon
     */
    WeaponImpl getBigBulletGun(Transform userPos, boolean isPlayerWeapon);

    WeaponImpl getGhiniGun(Transform userPos, boolean isPlayerWeapon);

}
