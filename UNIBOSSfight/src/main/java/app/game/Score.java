package app.game;

import app.util.Pair;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the scores of the game.
 */
public class Score {

    private static final int KILLS_SCORE = 2;

    private final Map<Integer, Pair<Integer, Integer>> levelStats;

    /**
     * Creates a new instance of the scores.
     */
    public Score() {
        this.levelStats = new HashMap<>();
    }

    /**
     * Returns the stats of the specified level.
     *
     * @param levelNumber the level index
     * @return a Pair with defeated enemies count as first value and
     * collected coins count as second value
     */
    public Pair<Integer, Integer> getLevelStat(final int levelNumber) {
        if (!this.levelStats.containsKey(levelNumber)) {
            throw new IllegalArgumentException("The level " + levelNumber
                    + " does not exist.");
        }

        return this.levelStats.get(levelNumber);
    }

    /**
     * Sets the given points to the specified level.
     *
     * @param levelNumber the level index
     * @param defeatedEnemies defeated enemies count
     * @param coinsCollected coins count
     */
    public void setLevelStats(final int levelNumber, final int defeatedEnemies, final int coinsCollected) {
        this.levelStats.put(levelNumber, new Pair<>(defeatedEnemies, coinsCollected));
    }

    /**
     * Returns the total points of the game.
     *
     * @return the sum of the points of all levels
     */
    public int getCumulativePoints() {
        return this.levelStats.values().stream()
                .map(x -> x.getFirstValue() * KILLS_SCORE + x.getSecondValue())
                .reduce(Integer::sum)
                .orElse(0);
    }
}
