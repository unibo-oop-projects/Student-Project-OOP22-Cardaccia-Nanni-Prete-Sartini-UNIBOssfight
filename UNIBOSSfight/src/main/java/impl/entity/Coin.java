package impl.entity;

import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * This class represents a coin which contributes to computing the final
 * score of the player by adding points every time a coin is collected.
 */
public abstract class Coin extends AbstractEntity {
    private final int value;

    /**
     * The constructor of the class which generates a new instance of Coin.
     * @param position the position of the coin
     * @param height the height of the coin
     * @param width the width of the coin
     * @param filename the file containing the sprite for the renderer
     */
    public Coin(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width,
                new SpriteRenderer(height, width, Color.YELLOW, filename));
        this.value = 1;
    }

    /**
     * @return the value of the coin
     */
    public int getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed(final Point2D position) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inputs input) {

    }

}
