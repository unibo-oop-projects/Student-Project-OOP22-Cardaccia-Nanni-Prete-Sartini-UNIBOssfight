package app.game;

import java.util.Arrays;

/**
 * This class implements the scores of the game.
 */
public class Score {

    private final int[] levelPoints;

    /**
     * Creates a new instance of the scores.
     *
     * @param levelCounts the number of levels of the game
     */
    public Score(final int levelCounts) {
        this.levelPoints = new int[levelCounts];
    }

    /**
     * Returns the points of the specified level.
     *
     * @param levelNumber the level index
     * @return the points of the level
     */
    public int getLevelPoints(final int levelNumber) {
        if (isOutOfRange(levelNumber)) {
            throw new IllegalArgumentException("The level " + levelNumber
                    + " does not exist.");
        }

        return this.levelPoints[levelNumber];
    }

    /**
     * Sets the given points to the specified level.
     *
     * @param levelNumber the level index
     * @param points the points of the level
     * @throws IllegalArgumentException when the level does not exist
     */
    public void setLevelPoints(final int levelNumber, final int points) {
        if (isOutOfRange(levelNumber)) {
            throw new IllegalArgumentException("The level " + levelNumber
                    + " does not exist.");
        }

        this.levelPoints[levelNumber] = points;
    }

    /**
     * Returns the total points of the game.
     *
     * @return the sum of the points of all levels
     */
    public int getCumulativePoints() {
        return Arrays.stream(this.levelPoints).reduce(Integer::sum).orElse(0);
    }

    private boolean isOutOfRange(final int index) {
        return index < 0 || index >= this.levelPoints.length;
    }
}
