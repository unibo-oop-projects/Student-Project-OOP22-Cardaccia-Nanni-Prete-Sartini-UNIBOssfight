package impl.component;

import core.component.Renderer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class models the component used to render the entities of the game.
 */
public class RendererImpl implements Renderer {

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
  public RendererImpl(final int height, final int width, final Color color) {
    this.height = height;
    this.width = width;
    this.color = color;
  }

  /**
   * This method returns the height of the rendered object.
   *
   * @return the height of the rendered object
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * This method returns the width of the rendered object.
   *
   * @return the width of the rendered object
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * The method used to render the entity.
   *
   * @param position the position of the entity
   * @param xDirection the direction on the x-axis the entity
   * @param yDirection the direction on the y-axis the entity
   * @param rotation the rotation of the entity
   * @return a Node that will be given as input to the Scene
   */
  @Override
  public Node render(final Point2D position, final int xDirection, final int yDirection, final double rotation) {
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
