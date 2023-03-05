package core.component;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Renderer implements Component{

  private int height;
  private int width;
  private Color color;

  public Renderer(int height, int width, Color color) {
    this.height = height;
    this.width = width;
    this.color = color;
  }

  /**
   * Data una posizione, una direzione e una rotazione restituisce un nodo renderizzato
   * che verrà inserita nella scena
   * @param position
   * @param direction
   * @param rotation
   * @return Node
   */
  public Node render(Point2D position, int direction, int rotation) {
    Rectangle rectangle = new Rectangle(
            position.getX() - width / 2,
            position.getY() - height,
            width,
            height
    );
    rectangle.setFill(this.color);

    return rectangle;
  }

  /**
   * @return l'altezza dell'oggetto renderizzato
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return la larghezza dell'oggetto renderizzato
   */
  public int getWidth() {
    return width;
  }
}
