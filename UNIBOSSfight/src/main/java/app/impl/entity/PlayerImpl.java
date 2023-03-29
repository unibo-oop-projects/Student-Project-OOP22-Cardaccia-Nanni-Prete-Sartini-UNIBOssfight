package app.impl.entity;

import app.core.component.Collider;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.ActiveEntity;
import app.core.entity.Bullet;
import app.impl.component.AnimatedSpriteRenderer;
import app.impl.component.ColliderImpl;
import app.impl.factory.WeaponFactory;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import app.util.Window;
import java.util.List;

public class PlayerImpl extends ActiveEntity {

    private transient final WeaponFactory weaponFactory = new WeaponFactory();
    private transient final Weapon weapon = weaponFactory.getPlayerWeapon(this.getTransform());
    private transient double rotation;
    private transient int coinsCollected;

    public PlayerImpl(final Transform position, final Integer height,
                      final Integer width, final String filename) {
        super(position, height, width,
                new AnimatedSpriteRenderer(height, width, Color.RED, filename));

        // TODO da togliere, compito della serializzazione
        setMaxXSpeed(10);
        setMaxYSpeed(20);
    }

    @Override
    public boolean isDisplayed(final Point2D position) {
        return true;
    }

    @Override
    public Node render(final Point2D position) {
        // TODO togliere exception generica e print
        try {
            return getRenderer().render(new Point2D(Window.getWidth() / 2,
                    this.getPosition().getY()), this.getDirection(), 1, 0);
        } catch (Exception e) {
            System.out.println("ERROR cannot load resource " + e);
        }

        return null;
    }

    public Node renderWeapon() {
        // TODO togliere exception generica e print
        try {
            return this.weapon.render(this.getDirection(), (int) this.rotation);
        } catch (Exception e) {
            System.out.println("ERROR cannot load resource " + e);
        }

        return null;
    }

    @Override
    public void init() {
        super.init();

        final var collider = new ColliderImpl();

        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) < 0
            && Math.abs(e.getPosition().getX() - this.getPosition().getX()) < e.getWidth() / 2.0 + this.getWidth() / 2.0) {
                setYSpeed(0);
            }
        });

        collider.addBehaviour(Collider.Entities.COIN, e -> {
            this.coinsCollected++;
            e.getHealth().destroy();
        });

        collider.addBehaviours(List.of(Collider.Entities.ENEMY,
                Collider.Entities.HARMFUL_OBSTACLE), e -> {
            setYSpeed(20);
            setXSpeed(20 * getHitbox().getCollisionSideOnX(e.getPosition().getX()));
            this.getHealth().damage(e.getDamage());
        });

        collider.addBehaviour(Collider.Entities.PLATFORM, e -> Platform.stop(this, e));

        setCollider(collider);
    }

    public void rotateWeapon(final Point2D mousePosition) {

        //TODO PORTARE ROTATE IN WEAPON
        //System.out.println(mousePosition);
        final double dx = mousePosition.getX() - Window.getWidth() / 2;
        final double dy = Window.getHeight() - mousePosition.getY() - weapon.getWeaponPosition().getPosition().getY();
        final double angle = -Math.toDegrees(Math.atan2(dy, dx));

        if(angle <= 90 && angle > -90){
            this.setDirection(1);
            this.weapon.setYDirection(1);
        }
        else
        {
            this.setDirection(-1);
            this.weapon.setYDirection(-1);
        }

        this.rotation = angle;
    }

    public void shoot(final Point2D target) {
        final Bullet newBullet = this.weapon.fire(target);
        newBullet.init();
        addBullet(newBullet);
    }

    public List<Node> getBulletsNodes() {
        return getBullets().stream().map(e -> e.render(getPosition())).toList();
    }

    public double getRotation() {
        return this.rotation;
    }

    @Override
    public void update(final Inputs input) {
        super.update(input);

        this.weapon.updatePosition(this.getTransform());
    }
}
