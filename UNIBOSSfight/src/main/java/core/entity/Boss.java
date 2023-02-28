package core.entity;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

public abstract class Boss extends Enemy {

    public Boss(final Transform position, final int height,
                final int width, final String filename) {
        super(position, height, width, new SpriteRenderer(height, width, Color.RED, filename));
    }

    Weapon getWeapon(){
        return null;
    }

}
