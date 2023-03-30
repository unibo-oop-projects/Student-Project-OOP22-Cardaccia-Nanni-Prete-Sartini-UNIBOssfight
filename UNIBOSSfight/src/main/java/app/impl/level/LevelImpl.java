package app.impl.level;

import app.core.entity.ActiveEntity;
import app.impl.component.TransformImpl;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LevelImpl implements Level {

    private final List<Entity> entities;
    private final PlayerImpl player;
    private transient int count;
    private transient boolean goLeft = true;

    public LevelImpl() {

        this.entities = new ArrayList<>();
        this.player = new PlayerImpl(new TransformImpl(new Point2D(0, 300), 0), 250, 250, "guido");
    }

    public void init() {
        this.player.init();
        this.entities.forEach(Entity::init);
    }

    @Override
    public void updateEntities() {

        this.entities.stream()
                .filter(e -> e instanceof ActiveEntity)
                .map(e -> (ActiveEntity) e)
                .filter(e -> e.isUpdated(this.getPlayerPosition()))
                .forEach(e -> {
                    e.update(Entity.Inputs.EMPTY);
                    var behaviour = e.getBehaviour().getFollowingBehaviour();
                    behaviour.ifPresent(b -> e.update(b.apply(getPlayer(), e)));
                });

        this.count++;
        if (this.count % 100 == 0) {
            this.goLeft = !this.goLeft;
        }

        this.entities.removeIf(e -> e.getHealth().isDead());
    }

    @Override
    public void updatePlayer(final Entity.Inputs input) {
        this.player.update(input);
    }

    @Override
    public List<Node> renderEntities() {
        return Stream.concat(this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition())), this.player.getBulletsNodes().stream()).toList();

    }

    public Node renderPlayer() {
        return this.player.render(this.player.getPosition());
    }

    public Node renderWeapon() {
        return this.player.renderWeapon();
    }

    @Override
    public void rotatePlayerWeapon(final Point2D point2D) {
        this.player.rotateWeapon(point2D);
    }

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
                .filter(e -> e.getCollider().isPresent())
                .toList();

        collidingEntities.forEach(ce -> allEntities.stream()
                .filter(e -> !e.equals(ce) && e.getHitbox().collide(ce.getHitbox()))
                .forEach(ce::manageCollision));
    }

    @Override
    public List<Entity> getEntities() {
        return List.copyOf(this.entities);
    }

    @Override
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }

    public Point2D getPlayerPosition() {
        return this.player.getPosition();
    }

    public void playerShoot(final Point2D target) {
        this.player.shoot(target);
    }

    public double getRotation() {
        return this.player.getRotation();
    }

    @Override
    public PlayerImpl getPlayer() {
        return player;
    }
}
