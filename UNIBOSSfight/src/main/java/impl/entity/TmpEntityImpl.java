package impl.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;


public class TmpEntityImpl extends AbstractEntity {
    private int health;

    public TmpEntityImpl(Transform position, Integer height, Integer width, String filename) {
        super(position, height, width, new SpriteRenderer(height, width, Color.GREEN, filename));

        //TODO implement health
    }

    @Override
    public void update(Inputs input) {

    }
}
