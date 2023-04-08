package app.impl.component;

import app.util.AppLogger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class is used to render an animation that loops over time.
 */
public class LoopSpriteRenderer extends SpriteRenderer {

    private static final int ANIMATION_DURATION = 24;

    private transient List<ImageView> preRenderedSprites;
    private transient int animationLength;
    private transient int cont;
    private transient int contDelay;
    private transient int maxDelay;
    private transient BooleanProperty isAnimationEnded; // NOPMD it's used like a boolean

    /**
     * Creates a new instance of the class SpriteRenderer.
     *
     * @param height   the height of the entity
     * @param width    the width of the entity
     * @param color    the color which will be given to the sprite
     * @param filename the name of the file containing the sprite
     *                 to be used for rendering
     */
    public LoopSpriteRenderer(final int height, final int width, final Color color, final String filename) {
        super(height, width, color, filename);
    }

    /**
     * @param position   the position of the entity
     * @param xDirection the direction on the x-axis of the entity
     * @param yDirection the direction on the y-axis of the entity
     * @param rotation   the rotation of the entity
     * @return the current sprite to render of the animation
     */
    @Override
    public Node render(final Point2D position, final int xDirection, final int yDirection, final double rotation) {
        this.setPrerendered(this.preRenderedSprites.get(cont % this.animationLength));
        if (cont % this.animationLength == 0) {
            this.isAnimationEnded.set(true);
        }

        if (this.contDelay % this.maxDelay == 0) {
            this.cont++;
        }

        this.contDelay++;

        return super.render(position, xDirection, yDirection, rotation);
    }

    /**
     *  Initialize the sprites of the loop and sets the animation length.
     */
    @Override
    public void init() {
        final String pathname = "assets/" + this.getFilename() +  "/" + this.getFilename();

        this.animationLength = getResourcesCount(pathname);
        this.maxDelay = LoopSpriteRenderer.ANIMATION_DURATION / this.animationLength;

        this.preRenderedSprites = IntStream.iterate(1, e -> e + 1)
                .limit(this.animationLength)
                .mapToObj(n -> getImage(pathname + n + ".png"))
                .map(this::createImageView)
                .toList();

        this.isAnimationEnded = new SimpleBooleanProperty(true);

        this.isAnimationEnded.addListener((observable, oldValue, newValue) -> {
            if (this.isAnimationEnded.get()) {
                this.cont = 0;
                this.contDelay = 0;
            }
        });
    }

    /**
     * sets the list of pre rendered sprites.
     *
     * @param preRenderedSprites the list to set
     */
    protected void setPreRenderedSprites(final List<ImageView> preRenderedSprites) {
        this.preRenderedSprites = preRenderedSprites;
    }

    /**
     * Sets the animation duration.
     *
     * @param animationLength the animation to set
     */
    public void setAnimationLength(final int animationLength) {
        this.animationLength = animationLength;
        this.maxDelay = LoopSpriteRenderer.ANIMATION_DURATION / this.animationLength;
    }

    /**
     * Returns the BooleanProperty that specifies if the animation is ended.
     *
     * @return the BooleanProperty
     */
    protected BooleanProperty getIsAnimationEnded() {
        return this.isAnimationEnded;
    }

    /**
     * Sets the BooleanProperty that specifies if the animation is ended.
     *
     * @param isAnimationEnded the BooleanProperty
     */
    protected void setIsAnimationEnded(final BooleanProperty isAnimationEnded) {
        this.isAnimationEnded = isAnimationEnded;
    }

    /**
     * Restarts the animation.
     */
    protected void resetAnimation() {
        this.cont = 0;
        this.contDelay = 0;
    }

    /**
     * Returns the number of resources that match the pattern /pathname*.png.
     *
     * @param pathname path to the resource
     * @return the resource count
     */
    protected int getResourcesCount(final String pathname) {

        int counter = 1;
        while (getClass().getClassLoader()
                .getResource(pathname + counter + ".png") != null) {
            counter++;
        }

        return counter - 1;
    }

    /**
     * Returns the image given the resource name.
     * @param resource the resource name
     * @return a new image
     */
    protected final Image getImage(final String resource) {

        final InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
        if (is != null) {
            return new Image(is, getWidth(), getHeight(), false, true);
        } else {
            AppLogger.getLogger().severe("Error occurred while loading " + resource);
            return null;
        }
    }
}
