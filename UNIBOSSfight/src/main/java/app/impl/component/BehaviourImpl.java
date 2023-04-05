package app.impl.component;

import app.core.component.Behaviour;
import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * This class implements the Behaviour, which is built using a Builder.
 * The methods of this class are used to set new entities' behaviours
 *
 */
public class BehaviourImpl implements Behaviour {

    private BiConsumer<ActiveEntity, Entity> jumpingBehaviour;
    private BiConsumer<ActiveEntity, Entity> bottomStoppingBehaviour;
    private BiConsumer<ActiveEntity, Entity> sideStoppingBehaviour;
    private BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs> followingBehaviour;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BiConsumer<ActiveEntity, Entity>> getJumpingBehaviour() {
        return Optional.ofNullable(this.jumpingBehaviour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJumpingBehaviour(final BiConsumer<ActiveEntity, Entity> consumer) {
        this.jumpingBehaviour = consumer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BiConsumer<ActiveEntity, Entity>> getBottomStoppingBehaviour() {
        return Optional.ofNullable(this.bottomStoppingBehaviour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBottomStoppingBehaviour(final BiConsumer<ActiveEntity, Entity> consumer) {
        this.bottomStoppingBehaviour = consumer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BiConsumer<ActiveEntity, Entity>> getSideStoppingBehaviour() {
        return Optional.ofNullable(this.sideStoppingBehaviour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSideStoppingBehaviour(final BiConsumer<ActiveEntity, Entity> consumer) {
        this.sideStoppingBehaviour = consumer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs>> getFollowingBehaviour() {
        return Optional.ofNullable(this.followingBehaviour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFollowingBehaviour(final BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs> function) {
        this.followingBehaviour = function;
    }
}
