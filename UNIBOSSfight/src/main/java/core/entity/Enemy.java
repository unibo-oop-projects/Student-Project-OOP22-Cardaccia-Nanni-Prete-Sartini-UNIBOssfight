package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;

import java.util.Optional;

public abstract class Enemy extends AbstractEntity {

    public Enemy(final Transform position, final int height,
                 final int width, final Renderer renderer) {
        super(position, height, width, renderer);
    }


}
