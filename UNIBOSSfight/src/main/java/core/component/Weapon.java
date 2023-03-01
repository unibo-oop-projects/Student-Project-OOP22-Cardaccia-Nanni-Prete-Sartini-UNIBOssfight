package core.component;

import core.entity.Bullet;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;


public interface Weapon extends Component {

    ImageView render(int direction, int rotation);

    Bullet fire(Point2D target);

    void render(GraphicsContext gc, int direction);

    ImageView render(int direction);
}
