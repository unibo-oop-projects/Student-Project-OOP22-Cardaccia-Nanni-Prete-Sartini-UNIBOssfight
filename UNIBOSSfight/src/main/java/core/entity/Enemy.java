package core.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;

public abstract class Enemy extends AbstractEntity {

    public Enemy(Transform position, int width, int height, Renderer renderer) {
        super(position, width, height, renderer);
    }

    public abstract int getDamage();
}
