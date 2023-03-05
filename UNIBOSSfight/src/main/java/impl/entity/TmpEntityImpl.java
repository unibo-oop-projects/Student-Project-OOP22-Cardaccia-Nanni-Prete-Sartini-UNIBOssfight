package impl.entity;

import core.component.Hitbox;
import core.component.Renderer;
import core.component.Transform;
import core.entity.AbstractEntity;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;
import util.Acceleration;
import util.Window;


public class TmpEntityImpl extends AbstractEntity {
    private int health;

    private int ySpeed = 0;

    public TmpEntityImpl(Transform position, Integer height, Integer width, String filename) {
        super(position, height, width, new SpriteRenderer(height, width, Color.GREEN, filename) {
            @Override
            public int getHeight() {
                return super.getHeight();
            }
        });

        //TODO implement health
    }

    @Override
    public void update(Inputs input) {



        switch (input) {
            case LEFT -> {this.position.move(-5, 0); this.direction = 1;}
            case RIGHT -> {this.position.move(5, 0); this.direction = -1;}
            case SPACE -> { if(!isJumping()) {
                this.ySpeed = -20;
                this.position.move(0, -1);

            }
                //this.position.move(0, ySpeed);
            }
            case EMPTY -> {
                this.position.move(0, ySpeed);
                this.ySpeed = this.isJumping() ? Acceleration.accelerate(this.ySpeed, 20, 1) : 0;
            }
        }

        //this.position.move(0, ySpeed);

        this.position.setGroundLevel();
        this.hitbox.update(this.getPosition());
    }

    private boolean isJumping() {
            return this.getPosition().getY() < Window.getHeight() - 1;
    }
}
