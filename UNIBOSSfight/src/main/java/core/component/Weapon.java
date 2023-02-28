package core.component;

import core.entity.Bullet;
import javafx.scene.canvas.GraphicsContext;

public interface Weapon extends Component {

    Bullet fire(Transform target);

    void render(GraphicsContext gc, int direction);
}
