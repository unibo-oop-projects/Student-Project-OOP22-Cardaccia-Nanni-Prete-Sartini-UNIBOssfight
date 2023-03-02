package impl.level;

import core.entity.Entity;
import core.level.Level;
import impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LevelImpl implements Level {

    private List<Entity> entities;
    private PlayerImpl player;
    private int cont = 0;
    private boolean goLeft = true;

    public LevelImpl(PlayerImpl player) {
        this.entities = new ArrayList<>();
        this.player = player;
    }

    public void init() {

    }

    public void updateEntities(){

        this.entities.forEach(e -> e.update(Entity.Inputs.EMPTY));
        if(this.goLeft)
        this.entities.forEach(e -> e.update(Entity.Inputs.RIGHT));
        else
        this.entities.forEach(e -> e.update(Entity.Inputs.LEFT));

        if (this.cont++ %100 == 0)
            this.goLeft = !this.goLeft;
    }

    public void updatePlayer(Entity.Inputs input){
        this.player.update(input);
    }

    @Override
    public List<ImageView> renderEntities() {
        return Stream.concat(this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition())), this.player.getBullets().stream()).toList();

    }

    public ImageView renderPlayer(){
        return this.player.render(this.player.getPosition());
    }

    public ImageView renderWeapon() {
        return this.player.renderWeapon();
    }


    public void renderEntities(GraphicsContext gc) {
        this.entities
                .stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .forEach(e -> e.render(gc, this.player.getPosition()));
        this.player.render(gc, this.player.getPosition());
    }

    @Override
    public void rotatePlayerWeapon(Point2D point2D) {
        this.player.rotateWeapon(point2D);
    }

    @Override
    public List<Entity> getEntities() {
        return List.copyOf(this.entities);
    }

    @Override
    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    public Point2D getPlayerPosition() {
        return this.player.getPosition();
    }
}
