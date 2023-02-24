package core.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Vector2d;

public abstract class Renderer implements Component{

  private int height;
  private int width;

  private Color color;

  public Renderer(int height, int width, Color color) {
    this.height = height;
    this.width = width;
    this.color = color;
  }

  @Override
  public void update(){

  }

  public void render(GraphicsContext gc, Vector2d position) {
    gc.setFill(this.color);
    gc.fillRect(position.getX() - width / 2 , position.getY()-height, width, height);
    gc.setFill(Color.RED);
    gc.fillOval(position.getX()-2, position.getY() - 2, 4,4);
  }
}
