package app.impl.component;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements an animation renderer.
 */
public class AnimationSpriteRenderer extends LoopSpriteRenderer {

    private static final String ANIMATION_IDLE = "idle";
    private static final String ANIMATION_WALK = "walk";
    private static final String ANIMATION_ATTACK = "attack";

    private transient Map<String, List<ImageView>> animations;
    private transient String previousAnimation;
    private String animation;


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
     * @param animation the name of the animation to set
     */
    public void setAnimation(final String animation) {
        if (this.previousAnimation == null || !this.previousAnimation.equals(animation)) {
            this.getIsAnimationEnded().set(false);
            this.animation = animation;
        }
    }

    /**
     * Initialize the renderer by loading the images of the sprites.
     */
    @Override
    public void init() {
        this.setIsAnimationEnded(new SimpleBooleanProperty(true));

        this.getIsAnimationEnded().addListener((observable, oldValue, newValue) -> {
            if (this.getIsAnimationEnded().get()) {
                resetAnimation();
                setPreRenderedSprites(this.animations.get(animation));
                setAnimationLength(this.animations.get(animation).size());
                this.previousAnimation = animation;
            }
        });


        this.animations = new HashMap<>();
        final List<String> animations = List.of(ANIMATION_IDLE, ANIMATION_WALK, ANIMATION_ATTACK);

        animations.forEach(e -> {

            final String pathname = "assets/" + this.getFilename() +  "/" + e + "/" + this.getFilename();
            final int animationLength = getResourcesCount(pathname);

            List<ImageView> preRenderedSprites;
            preRenderedSprites = initAnimation(animationLength, pathname);
            this.animations.put(e, preRenderedSprites);
        });


        setPreRenderedSprites(this.animations.get(ANIMATION_IDLE));
        setAnimationLength(this.animations.get(ANIMATION_IDLE).size());
    }
}
