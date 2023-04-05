package app.impl.level;

import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.component.TransformImpl;
import app.impl.entity.Bullet;
import app.impl.entity.Player;
import app.util.AppLogger;
import app.util.Window;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * This class implements the level.
 */
public class LevelImpl implements Level {

    private static final int PLAYER_HEIGHT = 250;
    private static final int PLAYER_WIDTH = 250;
    private final List<Entity> entities;
    private final Player player;
    private transient Image bg;

    /**
     * Creates a new instance of the level.
     */
    public LevelImpl() {
        this.entities = new ArrayList<>();
        this.player = new Player(
                new TransformImpl(new Point2D(0, 0), 0),
                PLAYER_HEIGHT, PLAYER_WIDTH, "guido"
        );
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

        this.entities.removeIf(e -> e.getHealth().isDead());

        removeBullets();
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
    public Node renderBackground() {

        final Rectangle bgr = new Rectangle(0, 0, Window.getWidth(), Window.getHeight());


        // set fill for rectangle
        final ImagePattern imagePattern = new ImagePattern(
                this.bg,
                -(this.getPlayerPosition().getX() / 10),
                0,
                Window.getHeight() * 16 / 9,
                Window.getHeight(),
                false
        );

        bgr.setFill(imagePattern);

        return bgr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderEntities() {
        return Stream.of(this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition()))).reduce(Stream::concat).orElseGet(Stream::empty).toList();

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> renderUniqueEntities() {
        final List<Node> nodes = new ArrayList<>();
        nodes.add(renderPlayer());
        nodes.add(renderWeapon());
        return nodes;
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

        // Entity collisions
        final List<Entity> collidingEntities = this.entities.stream()
                .filter(e -> e instanceof ActiveEntity)
                .toList();

        collidingEntities.forEach(ce -> this.entities.stream()
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
        addEntity(this.player.shoot(target));
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
        try {
            this.bg = new Image(new FileInputStream("assets/background.png"));
        } catch (FileNotFoundException e) {
            AppLogger.getLogger().severe(e.getMessage());
        }
        this.player.init();
        this.entities.forEach(Entity::init);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.player.getHealth().isDead();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBullets() {
        this.entities.removeIf(
                e -> e.getType().equals(Bullet.class.getName())
                        && !((ActiveEntity) e).isUpdated(this.getPlayerPosition())
                        || e.getHealth().isDead()
        );
    }

    /**
     * Sets the background img.
     *
     * @param bg the image to set
     */
    protected void setBg(final Image bg) {
        this.bg = bg;
    }
}
