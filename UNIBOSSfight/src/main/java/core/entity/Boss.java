package core.entity;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

public abstract class Boss extends Enemy {

    public Boss(Transform position, int width, int height, String filename) {
        super(position, width, height, new SpriteRenderer(height, width, Color.RED, filename));
    }

    Weapon getWeapon(){
        return null;
    }

}
