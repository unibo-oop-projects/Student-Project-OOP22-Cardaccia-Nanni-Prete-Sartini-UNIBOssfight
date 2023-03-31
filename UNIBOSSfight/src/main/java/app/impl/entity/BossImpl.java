package app.impl.entity;

import app.core.component.Health;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Boss;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.HealthImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class BossImpl extends Boss {

    private Weapon weapon;
    private Health health;
    private final int rateOfFire;
    private  int rateOfFireCounter = 0;

    public BossImpl(final Transform startingPos, final int height, final int width,
                    final int health, final Weapon weapon, final int rateOfFire, final String filename) {
        super(startingPos, height, width, filename);

        this.weapon = weapon;
        this.health = new HealthImpl();
        this.rateOfFire = rateOfFire;

        //TODO health
    }

    public boolean isDead() {
        return  this.health.isDead();
    }

    @Override
    public Weapon getWeapon() { return this.weapon; }

    @Override
    public void update(final Inputs input) {
        super.update(input);

//        getTransform().move(getDirection(), 0);
//        getHitbox().update(getTransform().getPosition());
//
//        //TODO WORK IN PROGRESS, portare fire in nuovo metodo fire con input posizione ( e render[altezza] dello sprite del player) del player
//        //GESTIONE FUOCO AUTOMATICO
//        if(rateOfFireCounter >= 10){
//            //NO, DEVE RESTITUIRE UN BULLET/ AGGIUNGERLO ALLA SUA LISTA
//            this.addBullet(getWeapon().fire(new Point2D(100, 100)));
//            this.rateOfFireCounter = 0;
//        } else {
//            this.rateOfFireCounter++;
//        }

        this.getWeapon().updatePosition(this.getTransform());

    }

    @Override
    public Node render(Point2D position) {
        return super.render(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();

        setBehaviour(new BehaviourBuilderImpl()
                .addJumpOnTop()
                .addStopFromBottom()
                .addStopFromSide()
                .addFollow()
                .build());

        getCollider().ifPresent(c -> c.addBehaviour(BulletImpl.class.getName(),
                e -> getHealth().damage(e.getDamage())));
    }
}
