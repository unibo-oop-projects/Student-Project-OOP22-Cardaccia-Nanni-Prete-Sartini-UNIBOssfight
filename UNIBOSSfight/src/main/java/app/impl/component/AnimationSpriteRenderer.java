package app.impl.component;

import app.util.AppLogger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * This class implements an animation renderer.
 */
public class AnimationSpriteRenderer extends LoopSpriteRenderer {
    private transient Map<String, List<ImageView>> animations;
    private transient String previousAnimation;


    /**
     * Creates a new instance of the class AnimationSpriteRenderer.
     *
     * @param height   the height of the entity
     * @param width    the width of the entity
     * @param color    the color which will be given to the sprite
     * @param filename the name of the file containing the sprite
     *                 to be used for rendering
     */
    public AnimationSpriteRenderer(final int height, final int width, final Color color, final String filename) {
        super(height, width, color, filename);
    }

    /**
     * Sets the animation to be rendered.
     *
     * @param animation
     */
    public void setAnimation(final String animation) {
        if (this.previousAnimation == null || !this.previousAnimation.equals(animation)) {
            resetAnimation();
            setPreRenderedSprites(this.animations.get(animation));
            setAnimationLength(this.animations.get(animation).size());
            this.previousAnimation = animation;
        }
    }

    /**
     * Initialize the renderer by loading the images of the sprites.
     */
    @Override
    public void init() {
        this.animations = new HashMap<>();
        final List<String> animations = List.of("idle", "walk");
        animations.forEach(e -> {

            final String pathname = "assets/" + this.getFilename() +  "/" + e;
            final File directory = new File(pathname);
            final int animationLength = Objects.requireNonNull(directory.list()).length;

            List<ImageView> preRenderedSprites;
            preRenderedSprites = IntStream.iterate(1, n -> n + 1)
                    .limit(animationLength)
                    .mapToObj(n -> {
                        try {
                            return new Image(
                                    new FileInputStream(pathname
                                            + "/" + this.getFilename() + n + ".png"),
                                    getWidth(), getHeight(),
                                    false,
                                    true);
                        } catch (FileNotFoundException ex) {
                            AppLogger.getLogger().severe(ex.getMessage());
                            throw (RuntimeException) new RuntimeException(ex);
                        }
                    })
                    .map(this::createImageView)
                    .toList();
            this.animations.put(e, preRenderedSprites);
        });
    }
}
