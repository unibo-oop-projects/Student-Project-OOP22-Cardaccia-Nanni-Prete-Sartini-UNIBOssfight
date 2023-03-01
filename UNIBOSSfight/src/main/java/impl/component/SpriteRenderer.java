package impl.component;

import core.component.Renderer;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class SpriteRenderer extends Renderer {
    private final String filename;

    private final PathTransition pt = new PathTransition();

    private Image img;
    private int angle = 20;

    public SpriteRenderer(int height, int width, Color color, String filename) {

        super(height, width, color);

        this.filename = filename;

        try {
            System.out.print("assets/" + filename);
            this.img = new Image(new FileInputStream("assets/" + filename),
                    getWidth(), getHeight(),
                    false,
                    true);
        } catch (Exception e){
            System.out.println("ERRORE: risorsa non trovata");
        }

    }

    @Override
    public void render(GraphicsContext gc, Point2D position, int direction)  {
        try {
            gc.drawImage(
                    img,
                    position.getX() - direction * getWidth() / 2,
                    position.getY() - getHeight(),
                    direction * this.getWidth(),
                    this.getHeight()
            );
            gc.setFill(Color.RED);
            gc.fillOval(position.getX() - 2, position.getY() - 2, 4, 4);

        } catch (Exception e){
            System.out.println("ERRORE: risorsa non trovata");
        }
    }

    public ImageView render(Point2D position, int direction) {

        ImageView iv2 = new ImageView();

        iv2.setImage(this.img);
        iv2.setFitWidth(getWidth());
        iv2.setFitHeight(getHeight());
        iv2.setX(position.getX());
        iv2.setY(position.getY());
        iv2.setPreserveRatio(false);
        iv2.setSmooth(true);
        iv2.setCache(true);


        return iv2;
    }
}
