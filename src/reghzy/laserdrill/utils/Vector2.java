package reghzy.laserdrill.utils;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int a) {
        this.x = a;
        this.y = a;
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(int a) {
        this.x += a;
        this.y += a;
        return this;
    }

    public Vector2 subtract(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 subtract(int a) {
        this.x -= a;
        this.y -= a;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2) {
            Vector2 vector2 = (Vector2) obj;
            return vector2.x == this.x && vector2.y == this.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.x + (this.y << 15);
    }

    public Vector2 copy() {
        return new Vector2(this.x, this.y);
    }
}
