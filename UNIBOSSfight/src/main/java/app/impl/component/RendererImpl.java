package app.impl.component;

import app.core.component.Renderer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class implements the renderer.
 */
public class RendererImpl implements Renderer {

  private final transient int height;
  private final transient int width;
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
   * {@inheritDoc}
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * {@inheritDoc}
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
