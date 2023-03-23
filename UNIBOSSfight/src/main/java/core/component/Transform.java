package core.component;

import javafx.geometry.Point2D;

public interface Transform extends Component {

    void move(double x, double y);

    void moveOnGroundLevel();

    double getGroundLevel();

    void setGroundLevel(double yGround);

    boolean isUnderGroundLevel();

    void resetGroundLevel();

    Point2D getPosition();

    void moveTo(double x, double y);

    float getRotation();

    Transform copyOf();
}
