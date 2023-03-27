package impl.component;

import core.component.Behaviour;
import core.component.Transform;
import core.entity.Entity;

public class BehaviourImpl implements Behaviour {

    public BehaviourImpl() {

    }
    @Override
    public Entity.Inputs behave(Transform playerPosition, Transform entityPosition) {
        if (playerPosition.getPosition().getX() < entityPosition.getPosition().getX()) {
            return Entity.Inputs.LEFT;
        }
        if (playerPosition.getPosition().getX() > entityPosition.getPosition().getX()) {
            return Entity.Inputs.RIGHT;
        }
        if (playerPosition.getPosition().getX() <= entityPosition.getPosition().getX()) {
            return Entity.Inputs.SPACE;
        }
        return Entity.Inputs.EMPTY;
    }
}
