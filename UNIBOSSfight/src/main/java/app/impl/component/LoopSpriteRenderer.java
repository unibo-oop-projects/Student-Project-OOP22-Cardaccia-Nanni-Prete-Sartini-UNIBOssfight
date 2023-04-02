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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LoopSpriteRenderer extends SpriteRenderer{
    /**
     * Creates a new instance of the class SpriteRenderer.
     *
     * @param height   the height of the entity
     * @param width    the width of the entity
     * @param color    the color which will be given to the sprite
     * @param filename the name of the file containing the sprite
     *                 to be used for rendering
     */

    private transient List<Image> sprites;
    private transient List<ImageView> preRenderedSprites;
    private transient int animationLength;
    private transient int cont = 0;
    private transient int contDelay = 0;

    public LoopSpriteRenderer(int height, int width, Color color, String filename) {
        super(height, width, color, filename);
    }

    @Override
    public Node render(Point2D position, int xDirection, int yDirection, double rotation) {
        try {
            if (this.animationLength == 1)
                return super.render(position, xDirection,0 , rotation);
            else {

                this.setPrerendered(this.preRenderedSprites.get(cont % this.animationLength));
                if(contDelay++ % 4 == 0){
                    this.cont++;
                }
                return super.render(position, xDirection, yDirection, rotation);
            }
        }catch (Exception e) {
            return super.render(position, xDirection, 1, rotation);
        }
    }

    @Override
    public void init() {
        try {
            File directory=new File("assets/" + this.getFilename());
            this.animationLength = Objects.requireNonNull(directory.list()).length;
            if(this.animationLength > 1) {
                this.sprites = new ArrayList<>();
                this.preRenderedSprites = new ArrayList<>();
                IntStream.iterate(1, e -> e + 1)
                        .limit(this.animationLength)
                        .forEach(e -> {
                            try {
                                this.sprites.add(new Image(new FileInputStream("assets/" + this.getFilename() + "/" + this.getFilename() + e + ".png"),
                                        getWidth(), getHeight(),
                                        false,
                                        true));
                                this.preRenderedSprites.add(this.createImageView(this.sprites.get(this.sprites.size() - 1)));
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
            } else {
                this.setImg(new Image(new FileInputStream("assets/" + this.getFilename() + ".png"),
                        getWidth(), getHeight(),
                        false,
                        true));
            }
        } catch (Exception e){
            AppLogger.getLogger().warning(e.getMessage());
        }
    }
}
