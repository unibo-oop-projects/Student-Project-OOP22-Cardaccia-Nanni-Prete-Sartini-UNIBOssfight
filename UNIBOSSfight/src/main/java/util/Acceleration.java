package util;

public class Acceleration {

    public static int accellerate(int currentVelocity, int targetVelocity, int timeDelta){
        int velocityDelta = targetVelocity - currentVelocity;

        if(velocityDelta > timeDelta)
            return currentVelocity + timeDelta;

        if(velocityDelta < timeDelta)
            return currentVelocity - timeDelta;

        return targetVelocity;
    }


}
