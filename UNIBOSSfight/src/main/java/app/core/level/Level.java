package app.core.level;

import app.core.entity.Entity;
import app.impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import java.util.List;

public interface Level {

    List<Entity> getEntities();

    void addEntity(Entity e);

    void updateEntities();

    void updatePlayer(Entity.Inputs input);

    List<Node> renderEntities();

    void collision();

    void rotatePlayerWeapon(Point2D mousePosition);

    PlayerImpl getPlayer();
}
