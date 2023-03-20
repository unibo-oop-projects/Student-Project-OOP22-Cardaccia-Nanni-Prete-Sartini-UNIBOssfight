package impl.entity;

import core.component.Collider;
import core.component.Transform;
import core.component.Weapon;
import core.entity.AbstractEntity;
import core.entity.Bullet;
import core.entity.Entity;
import impl.component.AnimatedSpriteRenderer;
import impl.component.ColliderImpl;
import impl.component.SpriteRenderer;
import impl.component.WeaponImpl;
import impl.factory.WeaponFactory;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import util.Acceleration;
import util.Window;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl extends AbstractEntity {

    private transient double ySpeed = 0;
    private WeaponFactory weaponFactory = new WeaponFactory();
    private transient final Weapon weapon = weaponFactory.getPlayerWeapon(this.getTransform());
    private transient double rotation;
    private transient final List<Bullet> bullets = new ArrayList<>();
    private transient double xSpeed = 0;


    public PlayerImpl(final Transform position, final Integer height,
                      final Integer width, final String filename) {
        super(position, height, width,
                new AnimatedSpriteRenderer(height, width, Color.RED, filename));
    }
    @Override
    public boolean isDisplayed(final Point2D position) {
        return true;
    }

    @Override
    public Node render(final Point2D position) {
        try {
            return getRenderer().render(new Point2D(Window.getWidth() / 2,
                    this.getPosition().getY()), this.getDirection(), 0);
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
                    this.ySpeed = -20;
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

        getTransform().resetGroundLevel();
        getHitbox().update(this.getPosition());
    }

    private boolean isJumping() {
        return this.getPosition().getY() < getTransform().getGroundLevel();
    }

    @Override
    public void initCollider() {
        final var collider = new ColliderImpl();
        collider.addBehaviour(Collider.Entities.TMPENTITY, e -> {
            this.ySpeed = -20;
            this.xSpeed = -20 * this.getDirection();
        });

        collider.addBehaviour(Collider.Entities.PLATFORM, e -> {
                // TODO comportamento in base alla direzione della collisione
                if (e.getPosition().getY() - getPosition().getY() > 0) {
                    final int topSide = (int) e.getPosition().getY() - e.getHeight();
                    getTransform().setGroundLevel(topSide);
                    if (getTransform().isUnderGroundLevel()) {
                        getTransform().moveOnGroundLevel();
                    }
                } else if (e.getPosition().getY() - getPosition().getY() < 0) {
                    getTransform().moveTo((int) getPosition().getX(),
                            (int) e.getPosition().getY() + getHeight() + 1);
                    this.ySpeed = 0;
                } else {
                    getTransform().move(getIntersection(e), 0);
                }

        });

        setCollider(collider);
    }

    private int getIntersection(final Entity e) {
        final int side = (int) Math.signum(getPosition().getX() - e.getPosition().getX());
        final int wallSide = (int) e.getPosition().getX() + e.getWidth() / 2 * side;
        final int playerSide = (int) getPosition().getX() - getWidth() / 2 * side;

        return wallSide - playerSide;
    }

    public void rotateWeapon(final Point2D mousePosition) {
        this.rotation = getDirection()
                * (mousePosition.getY() / Window.getHeight() * 120 - 55);
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
