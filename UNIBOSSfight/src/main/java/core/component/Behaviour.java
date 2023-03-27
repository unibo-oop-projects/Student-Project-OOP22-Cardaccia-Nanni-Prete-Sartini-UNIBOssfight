package core.component;

import core.entity.Entity;

public interface Behaviour extends Component {

    Entity.Inputs behave();

}
