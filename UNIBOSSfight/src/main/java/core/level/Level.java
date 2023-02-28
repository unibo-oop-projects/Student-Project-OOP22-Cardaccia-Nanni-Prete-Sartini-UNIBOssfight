package core.level;

import core.entity.Entity;
import impl.entity.PlayerImpl;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public interface Level {

    List<Entity> getEntities();

    public void addEntity(Entity e);

    void updateEntities();

    void updatePlayer(Entity.Inputs input);

    void renderEntities(GraphicsContext gc);

    void collision();

}
