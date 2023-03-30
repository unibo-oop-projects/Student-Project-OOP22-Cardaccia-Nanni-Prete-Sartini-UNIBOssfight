package app.impl.entity;

import app.core.component.Collider;
import app.core.component.Health;
import app.core.component.Transform;
import app.core.component.Weapon;
import app.core.entity.Boss;
import app.core.entity.Bullet;
import app.impl.component.ColliderImpl;
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

        getTransform().move(getDirection(), 0);
        getHitbox().update(getTransform().getPosition());

        //TODO WORK IN PROGRESS, portare fire in nuovo metodo fire con input posizione ( e render[altezza] dello sprite del player) del player
        //GESTIONE FUOCO AUTOMATICO
        if(rateOfFireCounter >= 10){
            //NO, DEVE RESTITUIRE UN BULLET/ AGGIUNGERLO ALLA SUA LISTA
            this.addBullet(getWeapon().fire(new Point2D(100, 100)));
            this.rateOfFireCounter = 0;
        } else {
            this.rateOfFireCounter++;
        }

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

        final var collider = new ColliderImpl();

        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            if (this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) < 0) {
                setYSpeed(0);
            }
            this.update(Inputs.SPACE);
        });

        collider.addBehaviour(Collider.Entities.BULLET, e -> {
            final Bullet b = (Bullet) e;
            getHealth().damage(b.getDamage());
        });

        setCollider(collider);
    }
}
