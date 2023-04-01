package app.core.component;

import app.core.entity.Boss;

public interface BossFactory {

    /**
     * This method returns an instance the first boss of the game.
     *
     * @param startingPos starting position of the boss
     * @return an instance of the boss
     */
    Boss firstBoss(Transform startingPos);

}
