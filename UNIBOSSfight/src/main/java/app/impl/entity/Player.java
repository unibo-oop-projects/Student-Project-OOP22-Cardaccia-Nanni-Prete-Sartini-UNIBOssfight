package app.impl.entity;

import app.core.component.Collider;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.component.WeaponFactory;
import app.core.entity.ActiveEntity;
import app.core.entity.Bullet;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.AnimationSpriteRenderer;
import app.impl.component.ColliderImpl;
import app.impl.component.LoopSpriteRenderer;
import app.impl.factory.WeaponFactoryImpl;
import app.util.Angle;
import app.util.Window;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * This class implements the player.
 */
public class Player extends ActiveEntity {

    private static final int RECOIL_VELOCITY = 20;

    private transient WeaponFactory weaponFactory = new WeaponFactoryImpl();
    private transient Weapon weapon = weaponFactory.getPlayerWeapon(this.getTransform(), true);
    private transient int coinsCollected;

    /**
     * Creates a new instance of Player.
     *
     * @param position
     * @param height
     * @param width
     * @param filename
     */
    public Player(final Transform position, final Integer height,
                  final Integer width, final String filename) {
        super(position, height, width,
                new LoopSpriteRenderer(height, width, Color.RED, filename));
    }

    /**
     * @return the rendered image of the weapon.
     */
    public Node renderWeapon() {
        return this.weapon.render(this.getPosition(), this.getDirection(), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();
        this.weaponFactory = new WeaponFactoryImpl();
        this.weapon = this.weaponFactory.getPlayerWeapon(this.getTransform(), true);

        setBehaviour(new BehaviourBuilderImpl()
                .addJumpOnTop()
                .addStopFromBottom()
                .addStopFromSide()
                .build());

        final Collider collider = new ColliderImpl();

        collider.addBehaviour(Wall.class.getName(), e -> {
            Wall.stop(this, e);
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) < 0
                    && Math.abs(e.getPosition().getX() - this.getPosition().getX())
                    < e.getWidth() / 2.0 + this.getWidth() / 2.0) {
                setYSpeed(0);
            }
        });

        collider.addBehaviour(Coin.class.getName(), e -> {
            this.coinsCollected++;
            e.getHealth().destroy();
        });

        collider.addBehaviours(List.of(
                EnemyImpl.class.getName(),
                BossImpl.class.getName(),
                HarmfulObstacle.class.getName()), e -> {
            this.getRenderer().setIsDamaged();
            setYSpeed(Player.RECOIL_VELOCITY);
            setXSpeed(
                    Player.RECOIL_VELOCITY
                            * getHitbox().getCollisionSideOnX(e.getPosition().getX())
            );
            this.getHealth().damage(e.getDamage());
            //System.out.println(this.getHealth().getValue());
        });

        collider.addBehaviour(Platform.class.getName(), e -> Platform.jump(this, e));

        setCollider(collider);
    }

    /**
     * Rotates the weapon according to the mouse coordinates.
     *
     * @param mousePosition
     */
    public void rotateWeapon(final Point2D mousePosition) {

        final double angle = this.weapon.updateRotation(mousePosition);

        if (angle <= Angle.RIGHT_ANGLE && angle > -Angle.RIGHT_ANGLE) {
            this.setDirection(1);
        } else {
            this.setDirection(-1);
        }
    }

    /**
     * Creates a new bullet pointing the current mouse position.
     *
     * @param target
     */
    public void shoot(final Point2D target) {
        final Bullet newBullet = this.weapon.fire(target);
        newBullet.init();
        addBullet(newBullet);
    }

    /**
     * @return The list of rendered bullets.
     */
    public List<Node> getBulletsNodes() {
        return getBullets().stream().map(e -> e.render(getPosition())).toList();
    }

    /**
     * Updates the player and sets the current animation for the renderer.
     *
     * @param input an element of the enum
     */
    @Override
    public void update(final Inputs input) {
        super.update(input);

        this.weapon.updatePosition(this.getTransform());
        if (input == Inputs.EMPTY && this.getRenderer() instanceof AnimationSpriteRenderer) {
            if (this.getXSpeed() != 0) {
                ((AnimationSpriteRenderer) this.getRenderer()).setAnimation("walk");
            } else {
                ((AnimationSpriteRenderer) this.getRenderer()).setAnimation("idle");
            }
        }
    }
}