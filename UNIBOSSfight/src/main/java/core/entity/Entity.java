package core.entity;

import core.component.Component;

public interface Entity {

    void update();

    Component getPosition();

}
