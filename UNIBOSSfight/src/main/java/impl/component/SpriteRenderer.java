package impl.component;

import core.component.Renderer;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * This class is used to generate the sprites representing the entities of the game.
 */
public class SpriteRenderer extends Renderer {
    private final String filename;
    private final PathTransition pt = new PathTransition();
    private final ArrayList<Image> sprites;
    private Image img;
    private int cont = 0;

    /**
     * Creates a new instance of the class SpriteRenderer.
     * @param height the height of the entity
     * @param width the width of the entity
     * @param color the color which will be given to the sprite
     * @param filename the name of the file containing the sprite
     *                 to be used for rendering
     */
    public SpriteRenderer(final int height, final int width,
                          final Color color, final String filename) {

        super(height, width, color);
        this.filename = filename;
        this.sprites = new ArrayList<>();

        try {
            this.img = new Image(new FileInputStream("assets/" + filename),
                    getWidth(), getHeight(),
                    false,
                    true);
        } catch (Exception e) {
            System.out.println("ERRORE: risorsa non trovata");
        }

        try {
            for (int i = 1; i <= 8; i++) {
                this.sprites.add(
                        new Image(new FileInputStream("assets/" + filename + i + ".png"),
                        getWidth(), getHeight(),
                        false,
                        true));
            }
        } catch (Exception e) {
            System.out.println("OPSSSS");
        }

    }

    /**
     * The method that actually returns the image representing
     * the sprite of the entity.
     * @param position the position of the entity
     * @param direction the direction of the entity
     * @param rotation the rotation of the entity
     * @return an ImageView if the asset given as input does exist,
     * the rectangle of the super class will be rendered otherwise
     */
    @Override
    public Node render(final Point2D position, final int direction, final int rotation) {

        if (this.img != null || this.sprites.size() > 0) {

            if (this.sprites.size() == 0) {
                final ImageView iv2 = new ImageView();

                iv2.setImage(this.img);

                iv2.setFitWidth(getWidth());
                iv2.setScaleX(direction);

                iv2.setFitHeight(getHeight());

                iv2.setRotate(rotation);

                iv2.setX(position.getX() - getWidth() / 2.0);
                iv2.setY(position.getY() - getHeight());

                iv2.setPreserveRatio(false);
                iv2.setSmooth(true);
                iv2.setCache(true);

                return iv2;
            } else  {
                final ImageView iv2 = new ImageView();

                iv2.setImage(this.sprites.get(cont++ % 8));

                iv2.setFitWidth(getWidth());
                iv2.setScaleX(direction);

                iv2.setFitHeight(getHeight());

                iv2.setRotate(rotation);

                iv2.setX(position.getX() - getWidth() / 2.0);
                iv2.setY(position.getY() - getHeight());

                iv2.setPreserveRatio(false);
                iv2.setSmooth(true);
                iv2.setCache(true);
                return iv2;
            }
        } else {
            return super.render(position, direction, rotation);
        }
    }
}
