package core.entity;

import core.component.Renderer;
import core.component.Transform;

public abstract class Enemy extends AbstractEntity {

    public Enemy(final Transform position, final int height,
                 final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }


}
