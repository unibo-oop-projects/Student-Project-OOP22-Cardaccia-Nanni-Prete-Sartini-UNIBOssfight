package app.impl.level;

import app.core.entity.ActiveEntity;
import app.core.entity.Boss;
import app.impl.component.TransformImpl;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.entity.Player;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * This class implements the level.
 */
public class LevelImpl implements Level {

    private final List<Entity> entities;
    private final Player player;

    /**
     * Creates a new instance of the level.
     */
    public LevelImpl() {

        this.entities = new ArrayList<>();
        this.player = new Player(new TransformImpl(new Point2D(0, 300), 0), 250, 250, "guido");

//        this.boss = new BossFactoryImpl().firstBoss(new TransformImpl(new Point2D(0, 400), 0));
//        this.addEntity(this.boss);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntities() {

        this.entities.stream()
                .filter(e -> e instanceof ActiveEntity)
                .map(e -> (ActiveEntity) e)
                .filter(e -> e.isUpdated(this.getPlayerPosition()))
                .forEach(e -> {
                    e.update(Entity.Inputs.EMPTY);
                    final var behaviour = e.getBehaviour().getFollowingBehaviour();
                    behaviour.ifPresent(b -> e.update(b.apply(getPlayer(), e)));
                });

        //this.boss.shoot(this.player.getPosition());

        //TODO pulire
        /*this.entities.stream()
                .filter(e -> e instanceof Boss)
                .map(e -> (Boss) e)
                .filter(e -> e.isUpdated(this.getPlayerPosition()))
                .forEach(e -> {
                    e.getBehaviour().getShootingBehaviour().ifPresent(b -> b.accept(e, getPlayer()));
                });
         */

        this.entities.removeIf(e -> e.getHealth().isDead());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayer(final Entity.Inputs input) {
        this.player.update(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderEntities() {
        //TODO PULIRE
        /*return Stream.concat(this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition())), this.player.getBulletsNodes().stream()).toList();
                */

        return Stream.of(this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition())),
                this.player.getBulletsNodes().stream()).reduce(Stream::concat).orElseGet(Stream::empty).toList();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node renderPlayer() {
        return this.player.render(this.player.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node renderWeapon() {
        return this.player.renderWeapon();
    }

    @Override
    public List<Node> renderUniqueEntities(){
        return List.of(renderPlayer(), renderWeapon());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotatePlayerWeapon(final Point2D point2D) {
        this.player.rotateWeapon(point2D);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collision() {
        // Player collisions
        this.entities.stream()
                .filter(e -> this.player.getHitbox().collide(e.getHitbox()))
                .forEach(this.player::manageCollision);

        final List<Entity> allEntities = new ArrayList<>(this.entities);
        allEntities.addAll(this.player.getBullets());

        // Entity collisions
        final List<Entity> collidingEntities = allEntities.stream()
                .filter(e -> e instanceof ActiveEntity)
                .toList();

        collidingEntities.forEach(ce -> allEntities.stream()
                .filter(e -> !e.equals(ce) && e.getHitbox().collide(ce.getHitbox()))
                .forEach(ce::manageCollision));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getEntities() {
        return List.copyOf(this.entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPlayerPosition() {
        return this.player.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerShoot(final Point2D target) {
        this.player.shoot(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.player.init();
        this.entities.forEach(Entity::init);
    }

    @Override
    public boolean isOver() {
        return this.player.getHealth().isDead();
    }

}
