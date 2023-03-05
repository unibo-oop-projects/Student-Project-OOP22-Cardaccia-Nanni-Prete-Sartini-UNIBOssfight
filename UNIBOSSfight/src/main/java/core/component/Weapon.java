package core.component;

import core.entity.Bullet;
import javafx.geometry.Point2D;
import javafx.scene.Node;


public interface Weapon extends Component {

    Node render(int direction, int rotation);

    Bullet fire(Point2D target);

    Node render(int direction);
}
