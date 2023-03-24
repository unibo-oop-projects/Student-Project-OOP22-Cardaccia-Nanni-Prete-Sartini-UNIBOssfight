package core.component;

import core.entity.Entity;
import impl.entity.BossImpl;
import impl.entity.BulletImpl;
import impl.entity.Coin;
import impl.entity.EnemyImpl;
import impl.entity.HarmfulObstacle;
import impl.entity.Platform;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;
import impl.entity.Wall;

import java.util.List;
import java.util.function.Consumer;

/**
 * An interface modelling the behaviours of the entities on collisions.
 */
public interface Collider extends Component {

    /**
     * Enum with all the possible entities.
     */
    enum Entities {
        // TODO valutare se mettere questa enum in un file a parte

        /**
         * The temporary entity (will be removed).
         */
        TMPENTITY(TmpEntityImpl.class),

        /**
         * The player.
         */
        PLAYER(PlayerImpl.class),

        /**
         * The enemy.
         */
        ENEMY(EnemyImpl.class),

        /**
         * The boss.
         */
        BOSS(BossImpl.class),

        /**
         * The platform.
         */
        PLATFORM(Platform.class),

        /**
         * The wall.
         */
        WALL(Wall.class),

        /**
         * The flame.
         */
        HARMFUL_OBSTACLE(HarmfulObstacle.class),

        /**
         * The bullet.
         */
        BULLET(BulletImpl.class),

        /**
         * The coin.
         */
        COIN(Coin.class);

        private final Class<? extends Entity> type;

        Entities(final Class<? extends Entity> type) {
            this.type = type;
        }

        /**
         * Verifies if the parameter is the same runtime type of the one associated
         * to the value of the enumeration.
         * @param e comparing entity
         * @param <T> subtype of Entity
         * @return true if the type is the same, false if it's not
         */
        public <T extends Entity> boolean equals(final T e) {
            return type.isInstance(e);
        }
    }

    /**
     * Manages the collision executing the procedure associated to the colliding entity.
     *
     * @param e entity with which the caller collides
     */
    void manageCollision(Entity e);

    /**
     * Associates a behaviour to a specific entity.
     *
     * @param key enum value of the entity
     * @param value behaviour
     */
    void addBehaviour(Entities key, Consumer<Entity> value);

    /**
     * Associates a behaviour to a list of entities.
     *
     * @param keys the list of entities
     * @param value behaviour
     */
    void addBehaviours(List<Entities> keys, Consumer<Entity> value);
}
