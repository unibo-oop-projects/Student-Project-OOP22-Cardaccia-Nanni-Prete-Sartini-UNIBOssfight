package app;

import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.entity.Player;
import app.impl.level.LevelImpl;
import app.util.AppLogger;
import app.util.DataManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This class tests the entities.
 */
final class TestEntity {

    private static final int LEFT_COLLISION = -1;
    private static final int RIGHT_COLLISION = 1;
    private static final int UPPER_COLLISION = 1;
    private static final int BOTTOM_COLLISION = -1;
    private static final int LEFT_MOVEMENT = -2;

    private Level level;

    @BeforeEach
    void init() {
        this.level = new LevelImpl();
    }

    void init(final String filename) {
        try {
            this.level = new DataManager().loadLevel(filename);
            this.level.getPlayer().init();
            this.level.getEntities().forEach(Entity::init);
        } catch (final IOException e) {
            AppLogger.getLogger().severe("Errore nel caricamento del livello di test "
                    + e.getMessage());
        }
    }

    /**
     * This method tests the collision from the left side, when colliding
     * the entity should be moved back.
     */
    @Test
    void testSideCollision() {
        init("leftcollision.json");

        final Player player = this.level.getPlayer();
        final Entity entity = this.level.getEntities().get(0);

        // Collision from left side
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.RIGHT);
        }

        assertEquals(LEFT_COLLISION,
                player.getHitbox().getCollisionSideOnX(entity.getPosition().getX()));

        player.manageCollision(entity);

        assertEquals(entity.getHitbox().getLeftSide(), player.getHitbox().getRightSide());
    }

    /**
     * This method tests the upper collision, when the entity collides
     * its ground level must be set to the top of the collided entity.
     */
    @Test
    void testUpperCollision() {
        init("uppercollision.json");

        final Player player = this.level.getPlayer();
        final Entity entity = this.level.getEntities().get(0);

        // Collision from up
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.EMPTY);
        }

        assertEquals(UPPER_COLLISION,
                player.getHitbox().getCollisionSideOnY(entity.getPosition().getY()));

        player.manageCollision(entity);

        assertEquals(entity.getHitbox().getTopSide(), player.getHitbox().getBottomSide());
    }

    /**
     * Tests the bottom collision, the case in which the intersections are equals
     * and it should prefer the side collision, then the bottom collision when the
     * entity should be stopped from the top.
     */
    @Test
    void testBottomCollision() {
        init("bottomcollision.json");

        Player player = this.level.getPlayer();
        final Entity entity = this.level.getEntities().get(0);

        // Collision from side
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.SPACE);
        }

        assertEquals(BOTTOM_COLLISION,
                player.getHitbox().getCollisionSideOnY(entity.getPosition().getY()));
        assertEquals(RIGHT_COLLISION,
                player.getHitbox().getCollisionSideOnX(entity.getPosition().getX()));

        player.manageCollision(entity);
        player.update(Entity.Inputs.EMPTY);

        assertNotEquals(entity.getHitbox().getBottomSide() - 1,
                player.getHitbox().getTopSide());
        assertEquals(entity.getHitbox().getRightSide(),
                player.getHitbox().getLeftSide());

        player = this.level.getPlayer();
        player.getTransform().move(LEFT_MOVEMENT, 0);

        // Collision from bottom
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.SPACE);
        }

        player.manageCollision(entity);

        update(player, Entity.Inputs.EMPTY);

        assertEquals(entity.getHitbox().getBottomSide(),
                player.getHitbox().getTopSide());
        assertNotEquals(entity.getHitbox().getRightSide(),
                player.getHitbox().getLeftSide());
    }

    /**
     * Tests the damage to the player.
     */
    @Test
    void testDamage() {
        init("damage.json");

        int counter = 0;

        final Player player = this.level.getPlayer();
        final Entity entity = this.level.getEntities().get(0);

        final int collisionsToDeath = entity.getDamage() != 0 ? player.getHealth().getValue() / entity.getDamage() : 0;

        while (!player.getHealth().isDead() && collisionsToDeath != 0) {
            update(player, Entity.Inputs.RIGHT);

            if (player.getHitbox().collide(entity.getHitbox())) {
                player.manageCollision(entity);
                counter++;
            }
        }

        assertEquals(collisionsToDeath, counter);
    }

    void update(final ActiveEntity ac, final Entity.Inputs input) {
        ac.update(input);
        ac.update(Entity.Inputs.EMPTY);
    }
}
