package app.core.component;

import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * This class models the Behaviour component
 * which determines the actions of the entities.
 */
public interface Behaviour {

    /**
     * Returns the behaviour to jump.
     *
     * @return a consumer that takes as input an active entity
     * and the platform or the wall on which the entity is jumping
     */
    Optional<BiConsumer<ActiveEntity, Entity>> getJumpingBehaviour();

    /**
     * Sets the behaviour to jump.
     *
     * @param consumer a consumer that takes as input an active entity
     *                 and the platform or the wall on which the entity is jumping
     */
    void setJumpingBehaviour(BiConsumer<ActiveEntity, Entity> consumer);

    /**
     * Returns the behaviour to stop the entity from the bottom.
     *
     * @return a consumer that takes as input an active entity
     * and the wall which is stopping the entity from below
     */
    Optional<BiConsumer<ActiveEntity, Entity>> getBottomStoppingBehaviour();

    /**
     * Sets the behaviour to jump.
     *
     * @param consumer a consumer that takes as input an active entity
     *                 and the wall which is stopping the entity from below
     */
    void setBottomStoppingBehaviour(BiConsumer<ActiveEntity, Entity> consumer);

    /**
     * Returns the behaviour to stop the entity from the side.
     *
     * @return a consumer that takes as input an active entity
     * and the wall or the platform which is stopping the entity from the side
     */
    Optional<BiConsumer<ActiveEntity, Entity>> getSideStoppingBehaviour();

    /**
     * Sets the behaviour to stop the entity from the side.
     *
     * @param consumer a consumer that takes as input an active entity
     *                 and the wall or the platform which is stopping
     *                 the entity from the side
     */
    void setSideStoppingBehaviour(BiConsumer<ActiveEntity, Entity> consumer);

    /**
     * Returns the behaviour to follow the player.
     *
     * @return a function that takes as input two entities,
     * the player and the one following it, and returns an Input for the update method
     */
    Optional<BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs>> getFollowingBehaviour();

    /**
     * Sets the behaviour to follow the player.
     *
     * @param function that takes as input the player and the entity following the player
     *                 and returns an Input for the update method
     */
    void setFollowingBehaviour(BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs> function);

}
