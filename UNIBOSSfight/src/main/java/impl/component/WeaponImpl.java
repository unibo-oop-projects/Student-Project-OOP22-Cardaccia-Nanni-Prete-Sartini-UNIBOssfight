package impl.component;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import impl.entity.BulletImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.Window;

public class WeaponImpl implements Weapon {

    private int damage;
    private Transform userPos;
    private Renderer renderer;

    public WeaponImpl(Transform userPos, int damage, Renderer renderer){
        //TODO
        this.userPos = userPos;
        this.damage = damage;
        this.renderer = renderer;
    }

    @Override
    public ImageView render(int direction) {
        return null;
    }

    @Override
    public Node render(int direction, int rotation) {
        return this.renderer.render(new Point2D(Window.getWidth() / 2 + 10 * direction, this.userPos.getPosition().getY() + 80 - 110), direction, rotation);
    }

    @Override
    public Bullet fire(Point2D target) {
        return new BulletImpl(Transform.copyOf(this.userPos), 20, 20, new SpriteRenderer(20, 20, Color.BLACK, "gnu.png"), 1, target);
    }
}

