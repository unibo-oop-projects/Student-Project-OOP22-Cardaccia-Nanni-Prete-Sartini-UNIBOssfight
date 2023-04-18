package app.impl.level;

import app.core.component.BossFactory;
import app.core.entity.Boss;
import app.core.entity.Entity;
import app.impl.component.TransformImpl;
import app.impl.factory.BossFactoryImpl;
import app.util.AppLogger;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import java.io.InputStream;
import java.util.List;

/**
 * Class that extends the Level to implement the Boss room.
 */
public class BossLevel extends LevelImpl {

    private static final int BOSS_X_POSITION = 1600;
    private static final int BOSS_Y_POSITION = 300;
    private transient Boss boss;
    private transient int rateOfFireCounter;

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
     * This method returns the Boss of the level.
     *
     * @return the boss of the level
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "The Boss class cannot be cloned."
    )
    public Boss getBoss() {
        return this.boss;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderUniqueEntities() {
        final List<Node> nodes = super.renderUniqueEntities();
        nodes.addAll(List.of(renderBoss(this.getPlayerPosition())));

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

        final var followingBehaviour = boss.getBehaviour().getFollowingBehaviour();
        followingBehaviour.ifPresent(b -> boss.update(b.apply(getPlayer(), boss)));
        final var flyingBehaviour = boss.getBehaviour().getFlyingBehaviour();
        flyingBehaviour.ifPresent(b -> boss.update(b.apply(boss, getPlayer())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        final InputStream is = getClass().getClassLoader()
                .getResourceAsStream("assets/stages/aula-magna.png");
        if (is != null) {
            this.setBg(new Image(is));
        } else {
            AppLogger.getLogger().warning("Error occurred while loading background");
        }

        final BossFactory bossFactory = new BossFactoryImpl();

        switch (this.getLevelNumber()) {
            case 1 -> {
                this.boss = bossFactory.meteorBoss(new TransformImpl(
                        new Point2D(BOSS_X_POSITION, BOSS_Y_POSITION), 0));
            }
            case 2 -> {
                this.boss = bossFactory.flyingBoss(new TransformImpl(
                        new Point2D(BOSS_X_POSITION, BOSS_Y_POSITION), 0));
            }
            default -> {
                AppLogger.getLogger().severe("Il livello non esiste.");
            }
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLevelEnded() {
        return this.boss.getHealth().isDead();
    }
}
