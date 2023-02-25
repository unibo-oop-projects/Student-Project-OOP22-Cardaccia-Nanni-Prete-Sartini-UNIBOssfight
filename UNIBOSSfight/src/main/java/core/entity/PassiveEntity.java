package core.entity;

public interface PassiveEntity extends Entity {
    boolean isHarmful();

    int getDamage();

}
