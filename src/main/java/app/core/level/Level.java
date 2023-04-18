package app.core.level;

import app.core.entity.Entity;
import app.impl.entity.Player;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import java.util.List;

/**
 * This interface models the concept of the game level.
 */
public interface Level {

    /**
     * The number of coins in this level.
     */
    int COINS_COUNT = 16;

    /**
     * The number of enemies in this level.
     */
    int ENEMY_COUNT = 10;

    /**
     * Returns the number of the level.
     *
     * @return the number of the level
     */
    int getLevelNumber();

    /**
     * Returns the list of the entities in the level.
     *
     * @return the list of all entities
     */
    List<Entity> getEntities();

    /**
     * Adds an entity to the level.
     *
     * @param e the new entity
     */
    void addEntity(Entity e);

    /**
     * Calls the update method on all entities.
     */
    void updateEntities();

    /**
     * Updates the player.
     *
     * @param input the action the player should perform
     */
    void updatePlayer(Entity.Inputs input);

    /**
     * Returns the rendered background.
     *
     * @return the node of the background
     */
    Node renderBackground();

    /**
     * Calls the render method on all entities.
     *
     * @return the list of nodes of the entities
     */
    List<Node> renderEntities();

    /**
     * Renders the player of the level.
     *
     * @return the node of the player
     */
    Node renderPlayer();

    /**
     * Renders the weapon of the player.
     *
     * @return the node of the weapon
     */
    Node renderWeapon();

    /**
     * Returns the weapon and the player rendered separately.
     *
     * @return a list with the node of the player
     * and the node of the weapon
     */
    List<Node> renderUniqueEntities();

    /**
     * Makes the player shoot to the given target.
     *
     * @param target the target position of the shot
     */
    void playerShoot(Point2D target);

    /**
     * Checks the collisions between the entities and handles them.
     */
    void collision();

    /**
     * Performs a rotation on the player weapon given the position of the mouse.
     *
     * @param mousePosition the position of the mouse
     */
    void rotatePlayerWeapon(Point2D mousePosition);

    /**
     * Returns the player instance from the level.
     *
     * @return the player
     */
    Player getPlayer();

    /**
     * Returns the position of the player.
     *
     * @return the position of the player
     */
    Point2D getPlayerPosition();

    /**
     * Calls the init method on all entities.
     */
    void init();

    /**
     * Checks if the game is over.
     *
     * @return true if the player is dead and so the game is over,
     * false otherwise
     */
    boolean isOver();

    /**
     * Checks if the player reaches the end of the level.
     *
     * @return true if the player reaches the end of the level,
     * false otherwise
     */
    boolean isLevelEnded();

    /**
     * Removes destroyed and not rendered Bullets in the level.
     */
    void removeBullets();

    /**
     * Returns the count of the enemies defeated from the player in the level.
     *
     * @return the defeated enemies number
     */
    int getDefeatedEnemiesCount();
}
