package util;

/**
 * This utility class models the concept of acceleration.
 */
public final class Acceleration {

    private Acceleration() {

    }

    /**
     * This method is used to increase the speed of the entities.
     * @param currentVelocity the current velocity of the entity
     * @param targetVelocity the target velocity
     * @param timeDelta the time delta
     * @return an int
     */
    public static double accelerate(final double currentVelocity, final double targetVelocity,
                                 final double timeDelta) {

        if (currentVelocity == targetVelocity) {
            return targetVelocity;
        }

        final double velocityDelta = targetVelocity - currentVelocity;

        if (velocityDelta > timeDelta) {
            return currentVelocity + timeDelta;
        }

        if (velocityDelta < timeDelta) {
            return currentVelocity - timeDelta;
        }

        return targetVelocity;
    }

}
