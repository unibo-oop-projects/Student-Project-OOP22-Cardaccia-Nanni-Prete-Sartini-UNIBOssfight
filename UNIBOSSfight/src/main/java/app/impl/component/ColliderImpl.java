package app.impl.component;

import app.core.component.Collider;
import app.core.entity.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * This class implements the Collider.
 */
public class ColliderImpl implements Collider {

    private final Map<String, Consumer<Entity>> behaviours;

    /**
     * Creates a new instance of the Collider.
     */
    public ColliderImpl() {
        this.behaviours = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageCollision(final Entity e) {
        if (this.behaviours.containsKey(e.getType())) {
            this.behaviours.get(e.getType()).accept(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBehaviour(final String key, final Consumer<Entity> value) {
        this.behaviours.putIfAbsent(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBehaviours(final List<String> keys, final Consumer<Entity> value) {
        for (final String e : keys) {
            addBehaviour(e, value);
        }
    }
}
