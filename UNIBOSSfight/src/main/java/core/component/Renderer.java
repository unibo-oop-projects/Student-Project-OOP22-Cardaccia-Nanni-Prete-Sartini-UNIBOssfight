package core.component;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class models the component used to render the entities of the game.
 */
public abstract class Renderer implements Component {

  private transient final int height;
  private transient final int width;
  private final Color color;

  /**
   * Creates a new instance of the class Renderer.
   *
   * @param height the height of the entity
   * @param width the width of the entity
   * @param color the color which will be given to the rendered object
   */
  public Renderer(final int height, final int width, final Color color) {
    this.height = height;
    this.width = width;
    this.color = color;
  }

  /**
   * This method returns the height of the rendered object.
   *
   * @return the height of the rendered object
   */
  public int getHeight() {
    return height;
  }

  /**
   * This method returns the width of the rendered object.
   *
   * @return the width of the rendered object
   */
  public int getWidth() {
    return width;
  }

  /**
   * The method used to render the entity.
   *
   * @param position the position of the entity
   * @param direction the direction of the entity
   * @param rotation the rotation of the entity
   * @return a Node that will be given as input to the Scene
   */
  public Node render(final Point2D position, final int direction, final int rotation) {
    final Rectangle rectangle = new Rectangle(
            position.getX() - width / 2.0,
            position.getY() - height,
            width,
            height
    );
    rectangle.setFill(this.color);
    rectangle.setRotate(rotation);

    return rectangle;
  }

}
