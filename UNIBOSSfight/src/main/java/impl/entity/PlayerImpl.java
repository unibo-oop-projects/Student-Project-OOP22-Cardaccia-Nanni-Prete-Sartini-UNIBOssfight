package impl.entity;

import core.component.Collider;
import core.component.Renderer;
import core.component.Transform;
import core.component.Weapon;
import core.entity.AbstractEntity;
import core.entity.Bullet;
import impl.component.AnimatedSpriteRenderer;
import impl.component.ColliderImpl;
import impl.factory.WeaponFactory;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import util.Acceleration;
import java.util.ArrayList;
import util.Window;
import java.util.List;

public class PlayerImpl extends AbstractEntity {

    private transient double xSpeed = 0;
    private transient double ySpeed = 0;
    private final WeaponFactory weaponFactory = new WeaponFactory();
    private transient final Weapon weapon = weaponFactory.getPlayerWeapon(this.getTransform());
    private transient double rotation;
    private transient final List<Bullet> bullets = new ArrayList<>();
    private transient int coinsCollected = 0;


    public PlayerImpl(final Transform position, final Integer height,
                      final Integer width, final String filename) {
        super(position, height, width,
                new AnimatedSpriteRenderer(height, width, Color.RED, filename));
    }

    public PlayerImpl(final Transform position, final Integer height,
                      final Integer width, final Renderer renderer) {
        super(position, height, width, renderer);
    }

    @Override
    public boolean isDisplayed(final Point2D position) {
        return true;
    }

    @Override
    public Node render(final Point2D position) {
        try {
            return getRenderer().render(new Point2D(Window.getWidth() / 2,
                    this.getPosition().getY()), this.getDirection(), 1, 0);
        } catch (Exception e) {
            System.out.println("ERROR cannot load resource " + e);
        }

        return null;
    }

    public Node renderWeapon() {
        try {
            return this.weapon.render(this.getDirection(), (int) this.rotation);
        } catch (Exception e) {
            System.out.println("ERROR cannot load resource " + e);
        }

        return null;
    }

    @Override
    public void update(final Inputs input) {

        switch (input) {
            case LEFT -> {
                this.xSpeed =  Acceleration.accelerate(this.xSpeed, -10, 1);
                setDirection(-1);
            }
            case RIGHT -> {
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 10, 1);
                setDirection(1);
            }
            case SPACE -> {
                if (!isJumping()) {
                    this.ySpeed = -30;
                    getTransform().move(0, -1);
                }
            }
            case EMPTY -> {
                getTransform().move(this.xSpeed, ySpeed);
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 0, 0.5);
                this.ySpeed = this.isJumping()
                        ? Acceleration.accelerate(this.ySpeed, 20, 1) : 0;
                this.bullets.forEach(e -> e.update(Inputs.EMPTY));
                this.removeBullets();
            }
        }

        this.weapon.updatePosition(this.getPosition());

        getTransform().resetGroundLevel();
        getHitbox().update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() < getTransform().getGroundLevel();
    }

    @Override
    public void initCollider() {
        final var collider = new ColliderImpl();

        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) > 0
            && Math.abs(e.getPosition().getX() - this.getPosition().getX()) < e.getWidth() / 2 + this.getWidth() / 2) {
                this.ySpeed = 0;
            }
        });

        collider.addBehaviour(Collider.Entities.COIN, e -> {
            this.coinsCollected++;
            e.getHealth().destroy();
        });

        collider.addBehaviours(List.of(Collider.Entities.ENEMY,
                Collider.Entities.HARMFUL_OBSTACLE, Collider.Entities.BOSS), e -> {
            this.ySpeed = -20;
            this.xSpeed = 20 * getHitbox().getCollisionSideOnX(e.getPosition().getX());
            this.getHealth().damage(e.getDamage());
        });

        collider.addBehaviour(Collider.Entities.PLATFORM, e -> Platform.stop(this, e));

        setCollider(collider);
    }

    public void rotateWeapon(final Point2D mousePosition) {
        //this.rotation = getDirection()
                //* (mousePosition.getY() / Window.getHeight() * 120 - 55);

        final double dx = (mousePosition.getX() + getPosition().getX() - Window.getWidth() / 2)
                - getPosition().getX();
        final double dy = mousePosition.getY() - getPosition().getY() +30 + 75;
        final double angle = Math.toDegrees(Math.atan2(dy, dx));

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
        newBullet.initCollider();
        this.bullets.add(newBullet);
    }

    private void removeBullets() {
        this.bullets.removeIf(e -> !e.isDisplayed(this.getPosition())
                || e.getHealth().isDead());
    }

    public List<Node> getBulletsNodes() {
        return this.bullets.stream().map(e -> e.render(getPosition())).toList();
    }

    public List<Bullet> getBullets() {
        return new ArrayList<>(this.bullets);
    }

    public double getRotation() {
        return this.rotation;
    }
}
