package core.entity;

public interface ActiveEntity extends Entity {

    void setHealth();

    int getHealth();

    void attack();

    boolean isDead();
}
