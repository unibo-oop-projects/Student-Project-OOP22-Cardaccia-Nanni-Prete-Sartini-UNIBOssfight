package util;

public class Acceleration {

    public static int accelerate(final int currentVelocity, final int targetVelocity,
                                 final int timeDelta) {
        int velocityDelta = targetVelocity - currentVelocity;

        if (velocityDelta > timeDelta) {
            return currentVelocity + timeDelta;
        }

        if (velocityDelta < timeDelta) {
            return currentVelocity - timeDelta;
        }

        return targetVelocity;
    }


}
