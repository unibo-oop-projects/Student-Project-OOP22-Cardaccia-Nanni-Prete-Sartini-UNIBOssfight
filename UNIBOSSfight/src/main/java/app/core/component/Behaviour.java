package app.core.component;

import app.core.entity.Entity;

/**
 * This class models the behaviour component
 * which determines the actions of the entities.
 */
public interface Behaviour {

    /**
     * This method takes as input the position of two entities
     * and determines their actions by returning an input that
     * will be used to update them.
     *
     * @param playerPosition the position of the player
     * @param entityPosition the position of the entity
     * @return an Input used by the method that updates
     *         the entities
     */
    Entity.Inputs behave(Transform playerPosition, Transform entityPosition);

    void jumpOnTop(Entity collidingEntity, Entity platform);

}
