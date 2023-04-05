package app.impl.builder;

import app.core.component.Behaviour;
import app.core.component.BehaviourBuilder;
import app.core.entity.Entity;
import app.impl.component.BehaviourImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class implements the Behaviour.
 */
public class BehaviourBuilderImpl implements BehaviourBuilder {

    private boolean built;
    private final Behaviour behaviour = new BehaviourImpl();


    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourBuilder addJumpOnTop() {
        this.behaviour.setJumpingBehaviour((e1, e2) -> {
            e1.getTransform().setGroundLevel(e2.getHitbox().getTopSide());
            if (e1.getTransform().isUnderGroundLevel()) {
                e1.getTransform().moveOnGroundLevel();
            }
        });
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourBuilder addStopFromBottom() {
        this.behaviour.setBottomStoppingBehaviour((e1, e2) -> {
            e1.getTransform()
                    .moveTo(e1.getPosition().getX(),
                            e2.getPosition().getY() - e1.getHeight() - 1);
            e1.setYSpeed(0);
        });
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourBuilder addStopFromSide() {
        this.behaviour.setSideStoppingBehaviour((e1, e2) -> e1.getTransform()
                .move(e1.getHitbox().getIntersectionOnX(e2), 0));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourBuilder addFollow() {
        this.behaviour.setFollowingBehaviour((e1, e2) -> {
            //e1 player e2 quello che segue
            if (e1.getPosition().getX() < e2.getPosition().getX()) {
                return Entity.Inputs.LEFT;
            }
            if (e1.getPosition().getX() > e2.getPosition().getX()) {
                return Entity.Inputs.RIGHT;
            }
            return Entity.Inputs.EMPTY;
        });
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourBuilder addFlying() {
        this.behaviour.setFlyingBehaviour((activeEntity, player) -> {
            return null;
        });
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
            value="EI_EXPOSE_REP",
            justification="The Builder pattern require this type of return statement"
    )
    @Override
    public Behaviour build() {
        if (built) {
            throw new IllegalStateException();
        }
        built = true;
        return this.behaviour;
    }
}
