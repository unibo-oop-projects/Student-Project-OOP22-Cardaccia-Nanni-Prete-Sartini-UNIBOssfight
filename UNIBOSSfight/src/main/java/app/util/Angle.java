package app.util;

import javafx.geometry.Point2D;

/**
 * Util class to manage angles needed in the application.
 */
public class Angle {

    /**
     * Right angle value.
     */
    public static final int RIGHT_ANGLE = 90;

    private Angle() {
        throw new UnsupportedOperationException("This is a utility class"
                + " and cannot be instantiated");
    }

    /**
     * This method finds the angle in gradients between
     * two given points in the game's reference system.
     *
     * @param point1 starting point of the vector
     * @param point2 finish point of the vector
     * @return the angle in gradients
     */
    public static double findAngle(final Point2D point1, final Point2D point2) {
        final double dx = point2.getX() - point1.getX();
        final double dy = point2.getY() - point1.getY();
        return  -Math.atan2(dy, dx); // Adjusted to new reference system
    }

}
