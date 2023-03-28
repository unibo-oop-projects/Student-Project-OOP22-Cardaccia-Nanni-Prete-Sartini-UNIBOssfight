package app.impl.component;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.FileInputStream;

/**
 * This class is used to generate the sprites representing the entities of the game.
 */
public class SpriteRenderer extends RendererImpl {
    private final String filename;
    private transient Image img;

    /**
     * Creates a new instance of the class SpriteRenderer.
     *
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

        try {
            this.img = new Image(new FileInputStream("assets/" + filename),
                    getWidth(), getHeight(),
                    false,
                    true);
        } catch (Exception e) {
            System.out.println("ERRORE: risorsa non trovata");
        }

    }

    /**
     * The method that actually returns the image representing
     * the sprite of the entity.
     *
     * @param position the position of the entity
     * @param xDirection the direction on the x-axis of the entity
     * @param yDirection the direction on the y-axis of the entity
     * @param rotation the rotation of the entity
     * @return an ImageView if the asset given as input does exist,
     * the rectangle of the super class will be rendered otherwise
     */
    @Override
    public Node render(final Point2D position, final int xDirection, final int yDirection, final double rotation) {

        if (this.img != null) {
            final ImageView iv2 = new ImageView();

            iv2.setImage(this.img);

            iv2.setFitWidth(getWidth());
            iv2.setScaleX(xDirection);
            iv2.setScaleY(yDirection);

            iv2.setFitHeight(getHeight());

            iv2.setRotate(rotation);

            iv2.setX(position.getX() - getWidth() / 2.0);
            iv2.setY(position.getY() - getHeight());

            iv2.setPreserveRatio(false);
            iv2.setSmooth(true);
            iv2.setCache(true);

            return iv2;
        } else {
            return super.render(position, xDirection, yDirection, rotation);
        }
    }

    protected Image getImg() {
        return this.img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
