package core.component;

public interface Weapon extends Component {

    int getDamage();

    void fire( double angle );
}
