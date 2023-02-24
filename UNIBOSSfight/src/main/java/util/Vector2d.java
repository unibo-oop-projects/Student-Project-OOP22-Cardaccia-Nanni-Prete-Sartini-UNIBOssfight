package util;

public class Vector2d {

    private float x;
    private float y;


    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void translate(int x, int y){
        this.x += x;
        this.y += y;
    }

    public Vector2d copyOf() {
        return new Vector2d(this.x, this.y);
    }

}
