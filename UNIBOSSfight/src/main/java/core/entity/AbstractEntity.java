package core.entity;

import core.component.Collider;
import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import util.Window;

import java.util.Optional;

/**
 *
 */
public abstract class AbstractEntity implements Entity {

    private final int height;
    private final int width;
    private Optional<Integer> damage;

    protected Transform position;
    protected Hitbox hitbox;
    protected Renderer renderer;
    protected Optional<Collider> collider;

    protected int direction = 1;

    /** Genera una nuova istanza della classe astratta AbstractEntity.
     * @param position
     * @param height
     * @param width
     * @param renderer
     */
    public AbstractEntity(
            final Transform position,
            final int height,
            final int width,
            final Renderer renderer
    ) {
        this.position = position;
        this.renderer = renderer;
        this.height = height;
        this.width = width;

        this.hitbox = new Hitbox(width / 2.0, height, getPosition()) {
        };

        initCollider();
    }

    /** Prende come input un elemento dell'enumerazione di Inputs e in base a quello
     * la classe eseguirà l'update.
     * @param input
     */
    public abstract void update(Inputs input);

    /**
     * @return la posizione dell'Entity
     */
    public Point2D getPosition() {
        return this.position.getPosition();
    }

    /**
     * @param position
     * @return Node generato dal renderer che verrà passato alla Scene
     */
    @Override
    public Node render(final Point2D position) {
        return this.renderer.render(
                new Point2D(
                        this.getPosition()
                                .subtract(position)
                                .add(Window.getWidth() / 2, 0)
                                .getX(),
                        this.getPosition().getY() - 57
                ),
                this.getDirection(),
                0
        );
    }

    /**
     * @param playerPosition
     * @return true se l'Entity si trova abbastanza vicino al player per poter essere
     * renderizzato all'interno della finestra di gioco, false altrimenti
     */
    @Override
    public boolean isDisplayed(final Point2D playerPosition) {
        //System.out.println(this.getClass() + " " + this.getPosition().getY() + " " + Window.getHeight());
        return Math.abs(this.getPosition().subtract(playerPosition).getX()) < Window.getWidth() / 2 &&
                this.getPosition().getY() <= Window.getHeight()
                && this.getPosition().getY() > 0;
    }

    /**
     * @return l'hitbox dell'entity
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * @return la direzione dell'entity
     */
    protected int getDirection() {
        return this.direction;
    }

    /**
     * @return il danno inferto dall'entity
     */
    public Optional<Integer> getDamage() {
        return this.damage;
    }

    /**
     * assegna all'entity il danno che infierisce
     * @param damage
     */
    protected void setDamage(Optional<Integer> damage) {
        this.damage = damage;
    }

    protected void initCollider() {
        this.collider = Optional.empty();
    }

    public Optional<Collider> getCollider() {
        return this.collider;
    }

    public void manageCollision(final Entity e) {
        this.collider.ifPresent(x -> x.manageCollision(e));
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
