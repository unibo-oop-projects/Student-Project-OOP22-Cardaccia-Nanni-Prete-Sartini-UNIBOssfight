package app.core.component;

public interface WeaponFactory {

    /**
     * Returns an instance of the player weapon.
     *
     * @param playerPos the player's position
     * @return an instance of the player weapon
     */
    Weapon getPlayerWeapon(Transform playerPos);

    /**
     * Returns an instance of the bigBullet weapon.
     *
     * @param userPos position of the weapon's user
     * @return an instance of the bigBullet weapon
     */
    Weapon getBigBulletGun(Transform userPos);

}
