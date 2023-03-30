package app.core.component;

import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * This class models the behaviour component
 * which determines the actions of the entities.
 */
public interface Behaviour {

    Optional<BiConsumer<ActiveEntity, Entity>> getJumpingBehaviour();

    void setJumpingBehaviour(BiConsumer<ActiveEntity, Entity> consumer);

    Optional<BiConsumer<ActiveEntity, Entity>> getBottomStoppingBehaviour();

    void setBottomStoppingBehaviour(BiConsumer<ActiveEntity, Entity> consumer);

    Optional<BiConsumer<ActiveEntity, Entity>> getSideStoppingBehaviour();

    void setSideStoppingBehaviour(BiConsumer<ActiveEntity, Entity> consumer);

    Optional<BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs>> getFollowingBehaviour();

    void setFollowingBehaviour(BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs> consumer);

}
