package app.impl.level;

import app.core.component.BossFactory;
import app.core.entity.Boss;
import app.core.entity.Entity;
import app.impl.component.TransformImpl;
import app.impl.factory.BossFactoryImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Class that extends the Level to implement the Boss room.
 */
public class BossLevel extends LevelImpl {

    private transient Boss boss;

    /**
     * Initialization of a new BossLevel instance.
     */
    public BossLevel() {
        super();

        //BossFactory bossFactory = new BossFactoryImpl();

        //this.boss = bossFactory.firstBoss(new TransformImpl(new Point2D(500, 300), 0));
    }

    /**
     * Method that return the node to Render the Boss.
     *
     * @return The node of the Boss
     */
    public Node renderBoss(Point2D playerPosition) {
        return boss.render(playerPosition);
    }

    /**
     * Method that return the node to Render the Weapon.
     *
     * @return The node of the Weapon
     */
    public Node renderBossWeapon(Point2D playerPosition) {
        return this.boss.renderWeapon(playerPosition);
    }

    /**
     * A method that makes the Boss shoot.
     *
     * @param target
     */
    public void bossShoot(final Point2D target) {
        this.boss.shoot(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderEntities() {
        /*List<Node> nodes = new ArrayList<>(super.renderEntities());
        nodes.addAll(this.boss.getBulletsNodes());
        return nodes;*/

        return Stream.of(this.getEntities().stream()
                        .filter(e -> e.isDisplayed(this.getPlayer().getPosition()))
                        .map(e -> e.render(this.getPlayer().getPosition())),
                this.boss.getBulletsNodes().stream()).reduce(Stream::concat).orElseGet(Stream::empty).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderUniqueEntities() {
        List<Node> nodes = super.renderUniqueEntities();
        nodes.addAll(List.of(renderBoss(this.getPlayerPosition()), renderBossWeapon(this.getPlayerPosition())));

        return nodes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntities() {
        super.updateEntities();

        this.boss.update(Entity.Inputs.EMPTY);

        this.boss.shoot(this.getPlayerPosition());

        //TODO beahaviour
        var behaviour = boss.getBehaviour().getFollowingBehaviour();
        behaviour.ifPresent(b -> boss.update(b.apply(getPlayer(), boss)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();
        BossFactory bossFactory = new BossFactoryImpl();

        this.boss = bossFactory.firstBoss(new TransformImpl(new Point2D(1600, 300), 0));
        this.boss.init();
    }

    @Override
    public void collision() {
        super.collision();

        // Player collisions
        this.getEntities().stream()
                .filter(e -> this.boss.getHitbox().collide(e.getHitbox()))
                .forEach(this.boss::manageCollision);

        if (this.boss.getHitbox().collide(getPlayer().getHitbox())) {
            this.getPlayer().manageCollision(this.boss);
        }
    }
}