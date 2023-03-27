package impl.component;

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

public class AnimatedSpriteRenderer extends SpriteRenderer{
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
    private transient int animationLength;
    private transient int cont = 0;
    private transient int contDelay = 0;

    public AnimatedSpriteRenderer(int height, int width, Color color, String filename) {
        super(height, width, color, filename);

        try {
            File directory=new File("assets/" + filename);
            this.animationLength = Objects.requireNonNull(directory.list()).length;
            if(this.animationLength > 1) {
                this.sprites = new ArrayList<>();
                //TODO toglimi
                IntStream.iterate(1, e -> e +/*
                l a
                d e n
                n o n
                a p p r e z z a
                l a
                m i a
                a r t e
                */1)
                        .limit(this.animationLength)
                        .forEach(e -> {
                            try {
                                this.sprites.add(new Image(new FileInputStream("assets/" + filename + "/" + filename + e + ".png"),
                                        getWidth(), getHeight(),
                                        false,
                                        true));
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
            } else {
                this.setImg(new Image(new FileInputStream("assets/" + filename + ".png"),
                        getWidth(), getHeight(),
                        false,
                        true));
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Node render(Point2D position, int xDirection, int yDirection, double rotation) {
        try {
            if (this.animationLength == 1)
                return super.render(position, xDirection,0 , rotation);
            else {
                final ImageView renderedSprite = new ImageView();

                renderedSprite.setImage(this.sprites.get(cont % this.animationLength));
                if(contDelay++ % 4 == 0){
                    this.cont++;
                }

                renderedSprite.setFitWidth(getWidth());
                renderedSprite.setScaleX(xDirection);
                renderedSprite.setScaleY(yDirection);

                renderedSprite.setFitHeight(getHeight());

                renderedSprite.setRotate(rotation);

                renderedSprite.setX(position.getX() - getWidth() / 2.0);
                renderedSprite.setY(position.getY() - getHeight());

                renderedSprite.setPreserveRatio(false);
                renderedSprite.setSmooth(true);
                renderedSprite.setCache(true);
                return renderedSprite;
            }
        }catch (Exception e) {
            return super.render(position, xDirection, 1, rotation);
        }
    }
}
