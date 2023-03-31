package app.impl.component;

import app.core.component.Behaviour;
import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class BehaviourImpl implements Behaviour {

    private BiConsumer<ActiveEntity, Entity> jumpingBehaviour;
    private BiConsumer<ActiveEntity, Entity> bottomStoppingBehaviour;
    private BiConsumer<ActiveEntity, Entity> sideStoppingBehaviour;
    private BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs> followingBehaviour;
    private BiConsumer<ActiveEntity, ActiveEntity> shootingBehaviour;

    @Override
    public Optional<BiConsumer<ActiveEntity, Entity>> getJumpingBehaviour() {
        return Optional.ofNullable(this.jumpingBehaviour);
    }

    @Override
    public void setJumpingBehaviour(final BiConsumer<ActiveEntity, Entity> consumer) {
        this.jumpingBehaviour = consumer;
    }

    @Override
    public Optional<BiConsumer<ActiveEntity, Entity>> getBottomStoppingBehaviour() {
        return Optional.ofNullable(this.bottomStoppingBehaviour);
    }

    @Override
    public void setBottomStoppingBehaviour(final BiConsumer<ActiveEntity, Entity> consumer) {
        this.bottomStoppingBehaviour = consumer;
    }

    @Override
    public Optional<BiConsumer<ActiveEntity, Entity>> getSideStoppingBehaviour() {
        return Optional.ofNullable(this.sideStoppingBehaviour);
    }

    @Override
    public void setSideStoppingBehaviour(final BiConsumer<ActiveEntity, Entity> consumer) {
        this.sideStoppingBehaviour = consumer;
    }

    @Override
    public Optional<BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs>> getFollowingBehaviour() {
        return Optional.ofNullable(this.followingBehaviour);
    }

    @Override
    public void setFollowingBehaviour(final BiFunction<ActiveEntity, ActiveEntity, Entity.Inputs> consumer) {
        this.followingBehaviour = consumer;
    }

    @Override
    public Optional<BiConsumer<ActiveEntity, ActiveEntity>> getShootingBehaviour() {
        return Optional.ofNullable(this.shootingBehaviour);
    }

    @Override
    public void setShootingBehaviour(BiConsumer<ActiveEntity, ActiveEntity> consumer) {
        this.shootingBehaviour = consumer;
    }
}
