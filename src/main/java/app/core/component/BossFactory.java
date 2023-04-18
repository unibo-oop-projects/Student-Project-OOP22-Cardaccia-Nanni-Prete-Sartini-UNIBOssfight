package app.core.component;

import app.core.entity.Boss;

/**
 * An interface modelling a Factory to create instances of Bosses.
 */
public interface BossFactory {

    /**
     * This method returns an instance of the first boss of the game.
     *
     * @param startingPos starting position of the boss
     * @return an instance of the boss
     */
    Boss meteorBoss(Transform startingPos);

    /**
     * This method returns an instance of a flying Boss.
     *
     * @param startingPos starting position of the boss
     * @return an instance of the boss
     */
    Boss flyingBoss(Transform startingPos);

}
