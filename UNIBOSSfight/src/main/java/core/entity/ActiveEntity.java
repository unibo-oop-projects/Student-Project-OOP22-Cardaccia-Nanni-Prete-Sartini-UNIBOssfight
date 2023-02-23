package core.entity;

public interface ActiveEntity extends Entity{

    void setDamage();

    float getDamage();

    void attack();

    boolean isDead();
}
