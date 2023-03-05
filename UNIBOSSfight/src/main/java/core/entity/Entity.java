package core.entity;

import core.component.Collider;
import core.component.Hitbox;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.Optional;

public interface Entity {

    /**
     * Enumerazione per la gestione dell'input delle  Entity
     */
    enum Inputs {
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

    /**
     * @return la posizione attuale dell'entity
     */
    Point2D getPosition();

    /**
     * @return l'hitbox dell'entity
     */
    Hitbox getHitbox();

    Optional<Collider> getCollider();

    void manageCollision(Entity e);

    int getWidth();

    int getHeight();

}
