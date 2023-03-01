package core.component;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Renderer implements Component{

  private int height;
  private int width;

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  private Color color;

  public Renderer(int height, int width, Color color) {
    this.height = height;
    this.width = width;
    this.color = color;
  }

  @Override
  public void update(){

  }

  public void render(GraphicsContext gc, Point2D position, int direction){
    gc.setFill(this.color);
    gc.fillRect(position.getX() - width / 2 , position.getY()-height, width, height);
    gc.setFill(Color.RED);
    gc.fillOval(position.getX()-2, position.getY() - 2, 4,4);
  }

  public ImageView render(Point2D position, int direction, int rotation) {
    Image img = null;
    try {
      img = new Image(new FileInputStream("assets/gnu.png"));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    ImageView iv2 = new ImageView();

    iv2.setImage(img);
    iv2.setFitWidth(-100);
    iv2.setX(position.getX() - width / 2);
    iv2.setY(position.getY() - height);
    iv2.setPreserveRatio(false);
    iv2.setSmooth(true);
    iv2.setCache(true);


    return iv2;
  }
}
