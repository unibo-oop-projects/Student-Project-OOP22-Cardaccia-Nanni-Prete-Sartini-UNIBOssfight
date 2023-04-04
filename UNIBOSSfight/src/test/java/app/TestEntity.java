package app;

import app.core.entity.ActiveEntity;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.entity.Player;
import app.util.AppLogger;
import app.util.DataManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestEntity {

    private static final int LEFT_COLLISION = -1;
    private static final int RIGHT_COLLISION = 1;
    private static final int UPPER_COLLISION = 1;
    private static final int BOTTOM_COLLISION = -1;

    Level level;

    public void init(final String filename) {
        try {
            this.level = new DataManager().loadLevel("src/test/resources/" + filename);
        } catch (Exception e) {
            AppLogger.getLogger().severe("errore nel caricamento del livello " + e.getMessage());
        }

        this.level.getPlayer().init();
        this.level.getEntities().forEach(Entity::init);
    }

    @Test
    public void testSideCollision() {
        init("leftcollision.json");

        Player player = this.level.getPlayer();
        Entity entity = this.level.getEntities().get(0);

        // Collision from left side
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.RIGHT);
        }

        assertEquals(LEFT_COLLISION, player.getHitbox().getCollisionSideOnX(entity.getPosition().getX()));

        player.manageCollision(entity);

        assertEquals(entity.getHitbox().getLeftSide(), player.getHitbox().getRightSide());
    }

    @Test
    public void testUpperCollision() {
        init("uppercollision.json");

        Player player = this.level.getPlayer();
        Entity entity = this.level.getEntities().get(0);

        // Collision from up
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.EMPTY);
        }

        assertEquals(UPPER_COLLISION, player.getHitbox().getCollisionSideOnY(entity.getPosition().getY()));

        player.manageCollision(entity);

        assertEquals(entity.getHitbox().getTopSide(), player.getHitbox().getBottomSide());
    }

    @Test
    public void testBottomCollision() {
        init("bottomcollision.json");

        Player player = this.level.getPlayer();
        Entity entity = this.level.getEntities().get(0);

        // Collision from side
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.SPACE);
        }

        assertEquals(BOTTOM_COLLISION, player.getHitbox().getCollisionSideOnY(entity.getPosition().getY()));
        assertEquals(RIGHT_COLLISION, player.getHitbox().getCollisionSideOnX(entity.getPosition().getX()));

        player.manageCollision(entity);

        assertNotEquals(entity.getHitbox().getBottomSide() - 1, player.getHitbox().getTopSide());
        assertEquals(entity.getHitbox().getRightSide(), player.getHitbox().getLeftSide());

        player = this.level.getPlayer();
        player.getTransform().move(-2, 0);

        // Collision from bottom
        while (!player.getHitbox().collide(entity.getHitbox())) {
            update(player, Entity.Inputs.SPACE);
        }

        player.manageCollision(entity);

        update(player, Entity.Inputs.EMPTY);

        assertEquals(entity.getHitbox().getBottomSide(), player.getHitbox().getTopSide());
        assertNotEquals(entity.getHitbox().getRightSide(), player.getHitbox().getLeftSide());
    }

    @Test
    public void testDamage() {
        init("damage.json");

        int counter = 0;

        Player player = this.level.getPlayer();
        Entity entity = this.level.getEntities().get(0);

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

    private void update(final ActiveEntity ac, final Entity.Inputs input) {
        ac.update(input);
        ac.update(Entity.Inputs.EMPTY);
    }
}