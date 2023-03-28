package app.impl.component;

import app.impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import app.util.Window;

public class PatternRender extends SpriteRenderer {

    private int xRatio;
    private int yRatio;

    /**
     * Creates a new instance of the class PatternRenderer.
     *
     * @param height   the height of the entity
     * @param width    the width of the entity
     * @param color    the color which will be given to the sprite
     * @param filename the name of the file containing the sprite
     *                 to be used for rendering
     */
    public PatternRender(int height, int width, Color color, String filename, int xRatio, int yRatio) {
        super(height, width, color, filename);
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    @Override
    public Node render(Point2D position, int xDirection, int yDirection, double rotation) {
        final Rectangle rect = new Rectangle(
                position.getX() - this.getWidth() / 2,
                position.getY() - this.getHeight(),
                this.getWidth(),
                this.getHeight()
        );

        ImagePattern imagePattern = new ImagePattern( this.getImg(),
                position.getX() - this.getWidth()/2,
                position.getY(),
                this.getImg().getWidth() / (getWidth() * this.xRatio),
                this.getImg().getHeight() / (getHeight() * this.yRatio),
                true
        );

        rect.setFill(imagePattern);

        return rect;
    }
}
