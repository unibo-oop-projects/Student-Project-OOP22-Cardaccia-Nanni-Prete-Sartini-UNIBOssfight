package core.component;

import javafx.geometry.Point2D;
import util.Window;

public class Transform implements Component {

    private Point2D position;
    private final float rotation;
    private double yGround = Window.getHeight(); // TODO height della window

    /**
     * Nuova istanza della classe Transform con posizione di partenza e rotazione.
     * @param position
     * @param rotation
     */
    public Transform(final Point2D position, final float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Trasla la posizione attuale con il vettore di componenti x e y.
     * @param x
     * @param y
     */
    public void move(final int x, final int y) {
        this.position = this.position.add(x, y);
    }

    /**
     * Riposiziona entity sul livello del terreno se questo vi si trova al di sotto.
     */
    public void setGroundLevel() {
        if (this.isUnderGroundLevel()) {
            this.position = new Point2D(this.position.getX(), yGround);
        }
    }

    public double getGroundLevel() {
        return this.yGround;
    }

    public void setGroundLevel(final double yGround) {
        this.yGround = yGround;
    }

    /**
     * @return true se l'entity si trova sotto il livello del terreno
     */
    public boolean isUnderGroundLevel() {
        return this.position.getY() > yGround;
    }

    public void resetGroundLevel() {
        setGroundLevel(Window.getHeight());
        setGroundLevel();
    }

    /**
     * @return Point2D della posizione corrente
     */
    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    /**
     * @param input
     * @return Transform che è l'esatta copia di quello passato in input
     */
    public static Transform copyOf(final Transform input) {
        return new Transform(
                new Point2D(input.getPosition().getX(), input.getPosition().getY()),
                input.rotation
        );
    }

    public void moveTo(final double x, final double y) {
        this.position = new Point2D(x, y);
    }
}
