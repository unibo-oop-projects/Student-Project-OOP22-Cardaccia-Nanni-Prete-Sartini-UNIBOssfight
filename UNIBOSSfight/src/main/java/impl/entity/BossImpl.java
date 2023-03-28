package impl.entity;

import core.component.Collider;
import core.component.Health;
import core.component.Transform;
import core.entity.Bullet;
import impl.component.ColliderImpl;
import impl.component.TransformImpl;
import core.component.Weapon;
import core.entity.Boss;
import impl.component.HealthImpl;
import util.Acceleration;

import java.util.List;

public class BossImpl extends Boss {

    private transient double xSpeed = 0;
    private transient double ySpeed = 0;
    private Weapon weapon;
    private Health health;

    public BossImpl(final Transform startingPos, final int height, final int width,
                    final int health, final Weapon weapon, final String filename) {
        super(startingPos, height, width, filename);

        this.weapon = weapon;
        this.health = new HealthImpl();

        //TODO health
    }

    public boolean isDead() {
        return  this.health.isDead();
    }

    private boolean isJumping(){ return this.getPosition().getY() < this.getTransform().getGroundLevel(); }

    @Override
    public Weapon getWeapon() { return this.weapon; }

    @Override
    public void update(final Inputs input) {

        switch (input){
            case LEFT -> {
                this.xSpeed = Acceleration.accelerate(this.xSpeed, -3, 1);
                setDirection(-1);
            }
            case RIGHT -> {
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 3, 1);
                setDirection(1);
            }
            case SPACE -> {
                if(!isJumping()){
                    this.ySpeed = -20;
                    getTransform().move(0, -1);
                }
            }
            case EMPTY -> {
                this.getTransform().move(this.xSpeed, this.ySpeed);
                this.xSpeed = Acceleration.accelerate(this.xSpeed, 0, 0.5);
                this.ySpeed = isJumping() ? Acceleration.accelerate(this.ySpeed, 20, 1) : 0;
            }
        }

        this.getTransform().resetGroundLevel();
        getHitbox().update(this.getPosition());
    }

    @Override
    public void initCollider() {
        final Collider collider = new ColliderImpl();

        collider.addBehaviour(Collider.Entities.WALL, e -> {
            Wall.stop(this, e);
            if(this.getHitbox().getCollisionSideOnY(e.getPosition().getY()) > 0){
                this.ySpeed = 0;
            }
            //this.update(Inputs.SPACE);
        });

        collider.addBehaviour(Collider.Entities.BULLET, e -> {
            final Bullet b = (Bullet) e;
            getHealth().damage(b.getDamage());
        });

        setCollider(collider);
    }
}
