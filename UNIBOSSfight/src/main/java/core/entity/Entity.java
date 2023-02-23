package core.entity;

import core.component.Component;
import util.Vector2d;

public interface Entity {

    void update();

    Vector2d getPosition();

}
