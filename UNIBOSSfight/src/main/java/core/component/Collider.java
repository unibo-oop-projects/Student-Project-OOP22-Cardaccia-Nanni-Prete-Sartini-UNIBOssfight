package core.component;


import core.entity.*;
import impl.entity.*;

import java.util.function.Consumer;

public interface Collider {

    enum Entities {
        // TODO valutare se mettere questa enum in un file a parte
        TMPENTITY(TmpEntityImpl.class),
        PLAYER(PlayerImpl.class),
        ENEMY(EnemyImpl.class),
        BOSS(BossImpl.class),
        PLATFORM(Platform.class),
        FLAME(Flame.class),
        SPINE(Spine.class),
        BULLET(BulletImpl.class);

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
