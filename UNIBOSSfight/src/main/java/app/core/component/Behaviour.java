package app.core.component;

import app.core.entity.Entity;

public interface Behaviour extends Component {

    Entity.Inputs behave(Transform playerPosition, Transform entityPosition);

}
