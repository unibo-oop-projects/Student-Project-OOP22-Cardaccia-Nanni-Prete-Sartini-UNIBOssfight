package impl.level;

import core.entity.Entity;
import core.level.Level;
import impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

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

    @Override
    public List<ImageView> renderEntities() {
        return this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition()))
                .toList();

    }

    public ImageView renderPlayer(){
        return this.player.render(this.player.getPosition());
    }


    public void renderEntities(GraphicsContext gc) {
        this.entities
                .stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .forEach(e -> e.render(gc, this.player.getPosition()));
        this.player.render(gc, this.player.getPosition());
    }

    @Override
    public void updateWeaponRotation(Point2D mousePosition) {
        //this.player.rotateWeapon(mousePosition);
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
