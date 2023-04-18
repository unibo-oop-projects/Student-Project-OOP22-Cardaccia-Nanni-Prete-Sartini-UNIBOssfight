package app.core.component;

import app.core.entity.Entity;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class models the collider component
 * which manages the behaviours of the entities
 * on collisions.
 */
public interface Collider {

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
    void addBehaviour(String key, Consumer<Entity> value);

    /**
     * Associates a behaviour to a list of entities.
     *
     * @param keys the list of entities
     * @param value behaviour
     */
    void addBehaviours(List<String> keys, Consumer<Entity> value);
}
