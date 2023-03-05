package impl.level;

import core.component.Transform;
import core.entity.Entity;
import core.level.Level;
import impl.entity.PlayerImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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

    public LevelImpl() {
        this.entities = new ArrayList<>();
        this.player = new PlayerImpl(
                new Transform(new Point2D(0, 300), 0),
                250,
                200,
                "testImage.png"
        );
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
    public List<Node> renderEntities() {
        return Stream.concat(this.entities.stream()
                .filter(e -> e.isDisplayed(this.player.getPosition()))
                .map(e -> e.render(this.player.getPosition())), this.player.getBullets().stream()).toList();

    }

    public Node renderPlayer(){
        return this.player.render(this.player.getPosition());
    }

    public Node renderWeapon() {
        return this.player.renderWeapon();
    }

    @Override
    public void rotatePlayerWeapon(Point2D point2D) {
        this.player.rotateWeapon(point2D);
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

    public Point2D getPlayerPosition() {
        return this.player.getPosition();
    }

    public void playerShoot(Point2D target){
        this.player.shoot(target);
    }
}
