package impl.component;

import core.component.Collider;
import core.entity.Entity;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class ColliderImpl implements Collider {

    private final Map<Entities, Consumer<Entity>> behaviours;

    public ColliderImpl() {
        this.behaviours = new EnumMap<>(Entities.class);
    }

    public void manageCollision(Entity e) {
        this.behaviours.entrySet().stream().filter(b -> b.getKey().equals(e))
                .findFirst().ifPresent(b -> b.getValue().accept(e));
    }

    public void addBehaviour(Entities key, Consumer<Entity> value) {
        this.behaviours.putIfAbsent(key, value);
    }
}
