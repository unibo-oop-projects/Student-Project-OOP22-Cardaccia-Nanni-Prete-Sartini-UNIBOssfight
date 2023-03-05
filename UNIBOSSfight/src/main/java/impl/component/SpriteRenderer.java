package impl.component;

import core.component.Renderer;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;

public class SpriteRenderer extends Renderer {
    private String filename;
    private final PathTransition pt = new PathTransition();
    private Image img;

    public SpriteRenderer(int height, int width, Color color, String filename) {

        super(height, width, color);
        this.filename = filename;

        try {
            this.img = new Image(new FileInputStream("assets/" + filename),
                    getWidth(), getHeight(),
                    false,
                    true);
        } catch (Exception e){
            System.out.println("ERRORE: risorsa non trovata");
        }

    }

    /**
     * @param position
     * @param direction
     * @param rotation
     * @return ImageView se la l'asset passato come argomento esiste, altrimenti verrà
     * renderizzato il rettangolo della classe padre
     */
    @Override
    public Node render(Point2D position, int direction, int rotation) {

        if(this.img != null) {

            ImageView iv2 = new ImageView();

            iv2.setImage(this.img);

            iv2.setFitWidth(getWidth());
            iv2.setScaleX(direction);

            iv2.setFitHeight(getHeight());

            iv2.setRotate(rotation);

            iv2.setX(position.getX() - getWidth() / 2);
            iv2.setY(position.getY() - getHeight());

            iv2.setPreserveRatio(false);
            iv2.setSmooth(true);
            iv2.setCache(true);

            return iv2;
        } else {
            return super.render(position, direction, rotation);
        }
    }
}
