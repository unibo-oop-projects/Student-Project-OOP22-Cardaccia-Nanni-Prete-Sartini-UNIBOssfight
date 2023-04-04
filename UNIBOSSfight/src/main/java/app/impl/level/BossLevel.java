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
    private int rateOfFireCounter = 0;

    /**
     * Initialization of a new BossLevel instance.
     */
    public BossLevel() {
        super();
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

        if(this.boss.isUpdated(this.getPlayerPosition())) {
            if (this.rateOfFireCounter >= this.boss.getRateOfFire()) {
                addEntity(this.boss.shoot(this.getPlayerPosition()));
                rateOfFireCounter = 0;
            } else {
                rateOfFireCounter++;
            }
        }

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

        //System.out.println(getEntities());
        // Player collisions
        this.getEntities().stream()
                .filter(e -> this.boss.getHitbox().collide(e.getHitbox()))
                .forEach(this.boss::manageCollision);

        if (this.boss.getHitbox().collide(getPlayer().getHitbox())) {
            this.getPlayer().manageCollision(this.boss);
        }
    }
}