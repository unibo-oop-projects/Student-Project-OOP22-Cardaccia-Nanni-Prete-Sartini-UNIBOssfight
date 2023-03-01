package impl.level;

import core.entity.Entity;
import core.level.Level;
import impl.entity.PlayerImpl;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {

    private List<Entity> entities;
    private PlayerImpl player;

    public LevelImpl(PlayerImpl player) {
        this.entities = new ArrayList<>();
        this.player = player;
    }

    public void init() {

    }

    public void updateEntities(){
        this.entities.forEach(e -> e.update(Entity.Inputs.EMPTY));
    }

    public void updatePlayer(Entity.Inputs input){
        this.player.update(input);
    }



    public void renderEntities(GraphicsContext gc) {
        this.entities.stream().filter(e -> e.isDisplayed(this.player.getPosition())).forEach(e -> e.render(gc, this.player.getPosition()));
        this.player.render(gc, this.player.getPosition());
    }

    @Override
    public void collision() {
        // Player collisions
        this.entities.stream()
                .filter(e -> this.player.getHitbox().collide(e.getHitbox()))
                .forEach(this.player::manageCollision);


        // Entity collisions
        var collidingEntities = this.entities.stream()
                .filter(e -> e.getCollider().isPresent())
                .toList();

        collidingEntities.forEach(ce -> this.entities.stream()
                .filter(e -> !e.equals(ce) && e.getHitbox().collide(ce.getHitbox()))
                .forEach(ce::manageCollision));
    }

    @Override
    public List<Entity> getEntities() {
        return List.copyOf(this.entities);
    }

    @Override
    public void addEntity(Entity e) {
            this.entities.add(e);
    }
}
