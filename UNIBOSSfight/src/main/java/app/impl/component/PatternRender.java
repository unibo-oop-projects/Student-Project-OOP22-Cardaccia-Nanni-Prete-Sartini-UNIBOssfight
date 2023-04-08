package app.impl.component;

import app.util.AppLogger;
import app.util.Window;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import java.io.InputStream;

/**
 * This class implements a Pattern Renderer.
 */
public class PatternRender extends SpriteRenderer {

    private final double xRatio;
    private final double yRatio;

    /**
     * Creates a new instance of the class PatternRenderer.
     *
     * @param height   the height of the entity
     * @param width    the width of the entity
     * @param color    the color which will be given to the sprite
     * @param filename the name of the file containing the sprite
     *                 to be used for rendering
     * @param xRatio the ratio on the x-axis
     * @param yRatio the ratio on the y-axis
     */
    public PatternRender(
            final int height,
            final int width,
            final Color color,
            final String filename,
            final int xRatio,
            final int yRatio
    ) {
        super(height, width, color, filename);
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    /**
     * Render an object with pattern.
     *
     * @param position   the position of the entity
     * @param xDirection the direction on the x-axis of the entity
     * @param yDirection the direction on the y-axis of the entity
     * @param rotation   the rotation of the entity
     * @return a rendered object with a pattern texture
     */
    @Override
    public Node render(
            final Point2D position,
            final int xDirection,
            final int yDirection,
            final double rotation
    ) {
        final Rectangle rect = new Rectangle(
                position.getX() - this.getWidth() / 2.0,
                Window.getHeight() - position.getY() - getHeight(),
                this.getWidth(),
                this.getHeight()
        );

        final Image img;

        final InputStream is = getClass().getClassLoader()
                .getResourceAsStream("assets/" + getFilename());
        if (is != null) {
            img = new Image(is, getWidth() / xRatio, getHeight() / yRatio, false, false);
            final ImagePattern imagePattern = new ImagePattern(
                    img,
                    position.getX() - this.getWidth() / 2.0,
                    Window.getHeight() - getHeight() - position.getY(),
                    getWidth() / xRatio,
                    getHeight() / yRatio,
                    false
            );

            rect.setFill(imagePattern);

        } else {
            AppLogger.getLogger().severe("Error occurred while loading " + getFilename());
            rect.setFill(Color.RED);
        }

        return rect;
    }
}
