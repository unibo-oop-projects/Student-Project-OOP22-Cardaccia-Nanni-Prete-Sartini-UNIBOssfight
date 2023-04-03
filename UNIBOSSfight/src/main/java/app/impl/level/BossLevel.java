package app.impl.level;

import app.core.component.BossFactory;
import app.core.entity.Boss;
import app.core.entity.Entity;
import app.impl.component.TransformImpl;
import app.impl.factory.BossFactoryImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.List;


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

        BossFactory bossFactory = new BossFactoryImpl();

        this.boss = bossFactory.firstBoss(new TransformImpl(new Point2D(500, 300), 0));
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
    public Node renderBossWeapon() {
        return this.boss.renderWeapon();
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
//    @Override
//    public List<Node> renderEntities() {
//        List<Node> nodes = super.renderEntities();
//        nodes.add(this.boss.render(this.boss.getPosition()));
//        return nodes;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderUniqueEntities() {
        List<Node> nodes = super.renderUniqueEntities();
        nodes.addAll(List.of(renderBoss(this.getPlayerPosition()), renderBossWeapon()));

        return nodes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntities() {
        super.updateEntities();

        this.boss.update(Entity.Inputs.EMPTY);

        //TODO beahaviour
        //this.boss.update();
        var behaviour = boss.getBehaviour().getFollowingBehaviour();
        behaviour.ifPresent(b -> boss.update(b.apply(getPlayer(), boss)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();
        System.out.println("bosslevel");
        BossFactory bossFactory = new BossFactoryImpl();

        this.boss = bossFactory.firstBoss(new TransformImpl(new Point2D(500, 300), 0));
        this.boss.init();
    }
}
