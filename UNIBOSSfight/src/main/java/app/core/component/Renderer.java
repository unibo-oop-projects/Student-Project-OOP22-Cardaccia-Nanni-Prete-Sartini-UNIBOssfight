package app.core.component;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * This class models the component used to render the entities of the game.
 */
public interface Renderer {

    /**
     * This method returns the height of the rendered object.
     *
     * @return the height of the rendered object
     */
    int getHeight();

    /**
     * This method returns the width of the rendered object.
     *
     * @return the width of the rendered object
     */
    int getWidth();

    /**
     * The method used to render the entity.
     *
     * @param position the position of the entity
     * @param xDirection the direction on the x-axis the entity
     * @param yDirection the direction on the y-axis the entity
     * @param rotation the rotation of the entity
     * @return a Node that will be given as input to the Scene
     */
    Node render(Point2D position, int xDirection, int yDirection, double rotation);

    void init();
}
