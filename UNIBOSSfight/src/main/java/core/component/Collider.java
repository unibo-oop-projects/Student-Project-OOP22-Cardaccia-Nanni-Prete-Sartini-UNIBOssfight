package core.component;

import core.entity.Entity;
import impl.entity.EnemyImpl;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;

import java.util.function.Consumer;

public interface Collider {

    enum Entities {
        // TODO aggiungere le entity mancanti
        // TODO valutare se mettere questa enum in un file a parte
        TMPENTITY(TmpEntityImpl.class),
        PLAYER(PlayerImpl.class),
        ENEMY(EnemyImpl.class);

        private final Class<? extends Entity> type;

        Entities(final Class<? extends Entity> type) {
            this.type = type;
        }

        public <T extends Entity> boolean equals(T e) {
            return e.getClass().equals(type);
        }
    }

    void manageCollision(Entity e);

    void addBehaviour(Entities key, Consumer<Entity> value);
}
