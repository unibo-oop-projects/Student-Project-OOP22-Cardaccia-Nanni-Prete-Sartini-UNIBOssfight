package core.entity;

import core.component.Collider;
import core.component.Hitbox;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.Optional;

public interface Entity {

    /**
     * Enumerazione per la gestione dell'input delle  Entity
     */
    public enum Inputs {
        LEFT,
        RIGHT,
        SPACE,
        EMPTY
    }

    /**
     * @param position
     * @return true se l'entity è abbastanza vicina al player per rientrare nella finestra,
     * false altrimenti
     */
    boolean isDisplayed(Point2D position);

    /**
     * @param position
     * @return il nodo renderizzato dal Render component dell'Entity
     */
    Node render(Point2D position);

    /**
     * Esegue l'update di un'entità in base all'input che riceve
     * @param input
     */
    void update(Inputs input);

    Point2D getPosition();

    Hitbox getHitbox();

    Optional<Collider> getCollider();

    void manageCollision(Entity e);

    int getWidth();

    int getHeight();

}
