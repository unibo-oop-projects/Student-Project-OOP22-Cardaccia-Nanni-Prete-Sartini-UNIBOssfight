package app.impl.entity;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import javafx.geometry.Point2D;

/**
 * This class models a coin, which contributes to computing the final
 * score of the player by adding points every time a coin is collected.
 */
public class Coin extends AbstractEntity {

    /**
     * Creates a new instance of the class Coin.
     *
     * @param position the position of the coin
     * @param height the height of the coin
     * @param width the width of the coin
     * @param renderer the renderer
     */
    public Coin(final Transform position, final int height,
                final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }

}
