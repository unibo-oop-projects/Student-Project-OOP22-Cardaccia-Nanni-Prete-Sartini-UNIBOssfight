package impl.component;

import core.component.Renderer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpriteRenderer extends Renderer {
    private final String filename;

    public SpriteRenderer(int height, int width, Color color, String filename) {

        super(height, width, color);

        this.filename = filename;

    }

    @Override
    public void render(GraphicsContext gc, Point2D position)  {
        try {
            gc.drawImage(
                    new Image(
                            new FileInputStream("assets/" + filename),
                            getWidth(), getHeight(),
                            false,
                            false
                    ),
                    position.getX() - getWidth() / 2,
                    position.getY() - getHeight()
            );
            gc.setFill(Color.RED);
            gc.fillOval(position.getX() - 2, position.getY() - 2, 4, 4);
        } catch (Exception e){
            System.out.println("ERRORE: risorsa non trovata");
        }
    }
}
