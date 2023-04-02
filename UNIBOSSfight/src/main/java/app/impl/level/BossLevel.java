package app.impl.level;

import app.core.component.BossFactory;
import app.core.entity.Boss;
import app.core.entity.Entity;
import app.impl.component.TransformImpl;
import app.impl.factory.BossFactoryImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.List;

public class BossLevel extends LevelImpl {

    private transient final Boss boss;

    public BossLevel() {
        super();

        BossFactory bossFactory = new BossFactoryImpl();

        this.boss = bossFactory.firstBoss(new TransformImpl(new Point2D(500, 300), 0));
    }

    public Node renderBoss(){
        return boss.render(this.boss.getPosition());
    }

    public Node renderBossWeapon() {
        return this.boss.renderWeapon();
    }

    public void bossShoot(Point2D target) {
        this.boss.shoot(target);
    }

    @Override
    public List<Node> renderEntities() {
        List<Node> nodes = super.renderEntities();
        nodes.add(this.boss.render(this.boss.getPosition()));
        return nodes;
    }

    @Override
    public List<Node> renderUniqueEntities() {
        List<Node> nodes = super.renderUniqueEntities();
        nodes.addAll(List.of(renderBoss(), renderBossWeapon()));

        return nodes;
    }

    @Override
    public void updateEntities() {
        super.updateEntities();

        this.boss.update(Entity.Inputs.EMPTY);

        //TODO beahaviour
        //this.boss.update();
        var behaviour = boss.getBehaviour().getFollowingBehaviour();
        behaviour.ifPresent(b -> boss.update(b.apply(getPlayer(), boss)));

    }
}
