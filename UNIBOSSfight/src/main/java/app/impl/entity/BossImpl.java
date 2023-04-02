package app.impl.entity;

import app.core.component.Health;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Boss;
import app.core.entity.Bullet;
import app.impl.builder.BehaviourBuilderImpl;
import app.impl.component.HealthImpl;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.List;

public class BossImpl extends Boss {
    private transient Weapon weapon;
    private int rateOfFire;
    private int rateOfFireCounter = 0;

    public BossImpl(final Transform startingPos, final int height, final int width, final String filename) {
        super(startingPos, height, width, filename);
        this.setMaxXSpeed(1);
        this.setMaxYSpeed(1);

        //TODO TOGLIERE (SERIALIZZAZIONE)
        rateOfFire = 30;
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
                .addShooting()
                .build());

        getCollider().ifPresent(c -> c.addBehaviour(BulletImpl.class.getName(),
                e -> getHealth().damage(e.getDamage())));
    }

    public void shoot(final Point2D target) {
        if(rateOfFireCounter >= rateOfFire) {
            final Bullet newBullet = this.weapon.fire(target);
            newBullet.init();
            addBullet(newBullet);

            rateOfFireCounter = 0;
        }
        else {
            rateOfFireCounter++;
        }
    }

    public List<Node> getBulletsNodes() {
        return getBullets().stream().map(e -> e.render(getPosition())).toList();
    }

    @Override
    public void setWeapon(final Weapon weapon) { this.weapon = weapon; }

    //TODO INTERFACCIA?
    @Override
    public Node renderWeapon() {
        // TODO togliere exception generica e print
        try {
            return this.weapon.render(this.getPosition() ,this.getDirection(), 0);
        } catch (Exception e) {
            AppLogger.getLogger().warning("ERROR cannot load resource " + e);
        }

        return null;
    }
}
