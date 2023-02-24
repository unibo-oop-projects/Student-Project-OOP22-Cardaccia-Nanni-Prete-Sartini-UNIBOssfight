package core.component;

public interface Weapon extends Component {

    int getDamage();

    void fire(Transform target);
}
