package app.core.component;

/**
 * A Builder of the Behaviour component,
 * with methods used to add behaviours
 * and a method build() to be called just once.
 */
public interface BehaviourBuilder {

    /**
     * @return this, and add a behaviour to jump on platforms and walls
     */
    BehaviourBuilder addJumpOnTop();

    /**
     * @return this, and add a behaviour to stop when colliding with the bottom of a wall
     */
    BehaviourBuilder addStopFromBottom();

    /**
     * @return this, and add a behaviour to stop when colliding with the side of a wall
     */
    BehaviourBuilder addStopFromSide();

    /**
     * @return this, and add a behaviour to follow the player
     */
    BehaviourBuilder addFollow();

    /**
     * @return this, and add a behaviour to shoot the player
     */
    BehaviourBuilder addShooting();

    /**
     * @return the Behaviour built with all of the above
     */
    Behaviour build();
}
