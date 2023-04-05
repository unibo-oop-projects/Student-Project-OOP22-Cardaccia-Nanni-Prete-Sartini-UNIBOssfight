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

    private static final int BOSS_X_POSITION = 1600;
    private static final int BOSS_Y_POSITION = 300;
    private transient Boss boss;
    private int rateOfFireCounter;

    /**
     * Method that return the node to Render the Boss.
     *
     * @param playerPosition the position of the Player
     * @return the node of the Boss
     */
    public Node renderBoss(final Point2D playerPosition) {
        return boss.render(playerPosition);
    }

    /**
     * Method that return the node to Render the Weapon.
     *
     * @param playerPosition the position of the Player
     * @return The node of the Weapon
     */
    public Node renderBossWeapon(final Point2D playerPosition) {
        return this.boss.renderWeapon(playerPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderUniqueEntities() {
        final List<Node> nodes = super.renderUniqueEntities();
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

        if (this.boss.isUpdated(this.getPlayerPosition())) {
            if (this.rateOfFireCounter >= this.boss.getRateOfFire()) {
                addEntity(this.boss.shoot(this.getPlayerPosition()));
                rateOfFireCounter = 0;
            } else {
                rateOfFireCounter++;
            }
        }

        //TODO beahaviour
        final var behaviour = boss.getBehaviour().getFollowingBehaviour();
        behaviour.ifPresent(b -> boss.update(b.apply(getPlayer(), boss)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();
        final BossFactory bossFactory = new BossFactoryImpl();

        this.boss = bossFactory.firstBoss(new TransformImpl(new Point2D(BOSS_X_POSITION, BOSS_Y_POSITION), 0));
        this.boss.init();
    }

    /**
     * {@inheritDoc}
     */
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
