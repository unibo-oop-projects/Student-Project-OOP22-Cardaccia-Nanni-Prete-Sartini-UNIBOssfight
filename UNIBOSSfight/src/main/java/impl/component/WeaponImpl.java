package impl.component;

import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import core.entity.Bullet;
import core.entity.Entity;
import impl.entity.BulletImpl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    public void render(GraphicsContext gc, int direction){
        this.renderer.render(gc, this.userPos.getPosition().subtract(this.userPos.getPosition().getX()-300 - 5 * direction, 24), direction);
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public Bullet fire(Transform target) {
        return new BulletImpl(this.userPos, 10, 10, new SpriteRenderer(10, 10, Color.BLACK, "bullet.png"), 1, target.getPosition());
    }
}
