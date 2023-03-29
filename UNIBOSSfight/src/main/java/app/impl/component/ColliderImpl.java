package app.impl.component;

import app.core.component.Collider;
import app.core.entity.Entity;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * This class implements the Collider.
 */
public class ColliderImpl implements Collider {

    private final Map<Entities, Consumer<Entity>> behaviours;

    /**
     * Creates a new instance of the Collider.
     */
    public ColliderImpl() {
        this.behaviours = new EnumMap<>(Entities.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageCollision(final Entity e) {
        this.behaviours.entrySet().stream().filter(b -> b.getKey().isEquals(e))
                .findFirst().ifPresent(b -> b.getValue().accept(e));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBehaviour(final Entities key, final Consumer<Entity> value) {
        this.behaviours.putIfAbsent(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBehaviours(final List<Entities> keys, final Consumer<Entity> value) {
        for (final Entities e : keys) {
            addBehaviour(e, value);
        }
    }
}
