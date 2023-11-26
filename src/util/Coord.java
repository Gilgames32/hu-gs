package util;

import java.awt.Point;

public class Coord {
    public int x;
    public int y;

    public Coord(int setX, int setY) {
        x = setX;
        y = setY;
    }

    public Coord(Point p) {
        x = p.x;
        y = p.y;
    }

    public Coord add(Coord other) {
        return new Coord(x + other.x, y + other.y);
    }

    public Coord sub(Coord other) {
        return new Coord(x - other.x, y - other.y);
    }

    public Coord multiply(double lambda) {
        return new Coord((int) (x * lambda), (int) (y * lambda));
    }
}
