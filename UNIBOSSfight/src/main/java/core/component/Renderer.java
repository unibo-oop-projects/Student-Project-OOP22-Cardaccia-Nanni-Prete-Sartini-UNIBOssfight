package core.component;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public interface Renderer extends Component {
    int getHeight();

    int getWidth();

    Node render(Point2D position, int direction, int rotation);
}
