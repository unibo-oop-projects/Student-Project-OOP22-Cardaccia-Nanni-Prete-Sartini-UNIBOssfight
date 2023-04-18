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
     * @param isPlayerWeapon identifier of the Weapon owner
     * @return an instance of the player weapon
     */
    WeaponImpl getPlayerWeapon(Transform playerPos, boolean isPlayerWeapon);

    /**
     * Returns an instance of the bigBullet weapon.
     *
     * @param userPos position of the weapon's user
     * @param isPlayerWeapon identifier of the Weapon owner
     * @return an instance of the bigBullet weapon
     */
    WeaponImpl getBigBulletGun(Transform userPos, boolean isPlayerWeapon);

    /**
     * Returns an instance of the meteor weapon.
     *
     * @param userPos position of the weapon's user
     * @param isPlayerWeapon identifier of the Weapon owner
     * @return an instance of the meteor weapon
     */
    WeaponImpl getMeteorGun(Transform userPos, boolean isPlayerWeapon);

}
