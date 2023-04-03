package app.impl.component;

import app.util.AppLogger;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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

public class AnimationSpriteRenderer extends LoopSpriteRenderer {
    private transient Map<String, List<ImageView>> animations;
    private transient String previousAnimation;


    public AnimationSpriteRenderer(final int height, final int width, final Color color, final String filename) {
        super(height, width, color, filename);
    }

    public void setAnimation(final String animation) {
        if (this.previousAnimation == null || !this.previousAnimation.equals(animation)) {
            resetAnimation();
            setPreRenderedSprites(this.animations.get(animation));
            setAnimationLength(this.animations.get(animation).size());
            this.previousAnimation = animation;
        }
    }

    @Override
    public void init() {
        try {
            this.animations = new HashMap<>();
            List<String> animations = List.of("idle", "walk");
            animations.forEach(e -> {

                String pathname = "assets/" + this.getFilename() +  "/" + e;
                File directory = new File(pathname);
                int animationLength = Objects.requireNonNull(directory.list()).length;

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
                                throw new RuntimeException(ex);
                            }
                        })
                        .map(this::createImageView)
                        .toList();
                this.animations.put(e, preRenderedSprites);
            });
        } catch (Exception e){
            AppLogger.getLogger().severe(e.getMessage());
        }
    }
}
