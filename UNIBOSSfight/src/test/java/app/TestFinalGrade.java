package app;

import app.game.Score;
import app.util.AppLogger;
import app.util.DataManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This class tests the calculation of the final grade.
 */
final class TestFinalGrade {

    private static final int MAXIMUM_GRADE = 110;
    private static final int MINIMUM_GRADE = 0;
    private static final int OUT_OF_RANGE_GRADE = 115;
    private Score testScore;

    void loadScore(final String filename) {
        try {
            this.testScore = new DataManager().deserializeScore(filename);
        } catch (final IOException e) {
            AppLogger.getLogger().severe("Errore nel caricamento del file dei punteggi  "
                                         + e.getMessage());
        }
    }

    /**
     * This method tests the computing of the final grade
     * based on the points cumulated by the player, both
     * testing the maximum and the minimum score obtainable.
     */
    @Test
    void testComputingFinalGrade() {
        loadScore("testscore1.json");
        assertEquals(MAXIMUM_GRADE, this.testScore.computeFinalGrade());

        loadScore("testscore2.json");
        assertEquals(MINIMUM_GRADE, this.testScore.computeFinalGrade());
    }

    /**
     * This method tests that the final grade obtained doesn't go out of range.
     */
    @Test
    void testOutOfRangeScore() {
        loadScore("testscore1.json");
        assertNotEquals(OUT_OF_RANGE_GRADE, this.testScore.computeFinalGrade());
    }
}
