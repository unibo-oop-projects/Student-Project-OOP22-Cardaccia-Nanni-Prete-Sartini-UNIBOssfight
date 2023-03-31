package app.core.component;

public interface BehaviourBuilder {

    BehaviourBuilder addJumpOnTop();

    BehaviourBuilder addStopFromBottom();

    BehaviourBuilder addStopFromSide();

    BehaviourBuilder addFollow();

    BehaviourBuilder addShooting();

    Behaviour build();
}
