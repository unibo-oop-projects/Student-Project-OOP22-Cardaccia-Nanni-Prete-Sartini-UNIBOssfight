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

    private final int damage;
    private final Transform userPos;
    private final Renderer renderer;

    public WeaponImpl(final Transform userPos, final int damage, final Renderer renderer) {
        //TODO
        this.userPos = userPos;
        this.damage = damage;
        this.renderer = renderer;
    }

    @Override
    public ImageView render(final int direction) {
        return null;
    }

    @Override
    public Node render(final int direction, final int rotation) {
        return this.renderer.render(new Point2D(Window.getWidth() / 2 + 10 * direction,
                this.userPos.getPosition().getY() + 80 - 110), direction, rotation);
    }

    @Override
    public Bullet fire(final Point2D target) {
        return new BulletImpl(Transform.copyOf(this.userPos), 20, 20,
                new SpriteRenderer(20, 20, Color.BLACK, "gnu.png"),
                1, target, 20);
    }
}

