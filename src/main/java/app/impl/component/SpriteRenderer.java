package app.impl.component;

import app.util.AppLogger;
import app.util.Window;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.InputStream;

/**
 * This class is used to generate the sprites representing the entities of the game.
 */
public class SpriteRenderer extends RendererImpl {
    private final String filename;
    private transient ImageView prerendered;

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

        if (this.prerendered != null) {

            this.prerendered.setScaleX(xDirection);
            prerendered.setScaleY(yDirection);
            prerendered.setRotate(rotation);

            prerendered.setX(position.getX() - getWidth() / 2.0);
            prerendered.setY(Window.getHeight() - position.getY() - getHeight());

            prerendered.setEffect(null);


            if (this.getRemainingDamagedFrames() > 0) {
                final Blend blend = new Blend();
                final ColorInput topInput = new ColorInput(position.getX() - getWidth() / 2.0,
                        Window.getHeight() - position.getY() - getHeight(),
                        getWidth(),
                        getHeight(),
                        new Color(1, 0, 0, 0.5));

                //setting the top input to the blend object
                blend.setTopInput(topInput);

                //setting the blend mode
                blend.setMode(BlendMode.SRC_ATOP);

                prerendered.setEffect(blend);
            }

            return prerendered;
        } else {
            return super.render(position, xDirection, yDirection, rotation);
        }
    }

    /**
     * Sets the prerendered sprite.
     *
     * @param prerendered the prerendered sprite to render
     */
    public void setPrerendered(final ImageView prerendered) {
        this.prerendered = prerendered;
    }

    /**
     * Creates an ImageView from a given image.
     *
     * @param img image to render
     * @return the imageview created with the given image
     */
    protected ImageView createImageView(final Image img) {
        final ImageView prerenderedImage = new ImageView();

        prerenderedImage.setImage(img);

        prerenderedImage.setFitWidth(getWidth());

        prerenderedImage.setFitHeight(getHeight());

        prerenderedImage.setPreserveRatio(false);
        prerenderedImage.setSmooth(true);
        prerenderedImage.setCache(true);
        return prerenderedImage;
    }

    /**
     * Initialize the renderer creating the prerendered sprite.
     */
    @Override
    public void init() {

        final InputStream is = getClass().getClassLoader()
                .getResourceAsStream("assets/" + filename);
        if (is != null) {
            this.prerendered = createImageView(new Image(is, getWidth(), getHeight(),
                    false, true));
        } else {
            AppLogger.getLogger().severe("Error occurred while loading " + filename);
            this.prerendered = null;
        }
    }

    /**
     * get the filename of the sprite.
     *
     * @return the filename of the rendered image
     */
    protected String getFilename() {
        return this.filename;
    }
}
