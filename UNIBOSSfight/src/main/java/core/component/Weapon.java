package core.component;

import core.entity.Bullet;

public interface Weapon extends Component {

    Bullet fire(Transform target);
}
