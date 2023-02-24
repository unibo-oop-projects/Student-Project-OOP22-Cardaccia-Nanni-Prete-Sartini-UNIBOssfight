package core.component;

import com.sun.javafx.scene.text.TextLayout;
import util.Vector2d;

public abstract class Hitbox implements Component {

    private int lateralOffset;
    private int Height;
    private Vector2d currentPosition;
    private Vector2d lastPosition;

    public Hitbox(int latOffset, int height, Vector2d startingPosition){
        this.lateralOffset = latOffset;
        this.Height = height;
        this.currentPosition = startingPosition;
        this.lastPosition = startingPosition;
    }

    boolean Collide(Hitbox target){
        return false;
    }


}
