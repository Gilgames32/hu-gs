package util;

import java.awt.Point;

public class Coord {
    public int x;
    public int y;

    /**
     * Parameterized constructor
     * 
     * @param setX initial x coordinate
     * @param setY initial y coordinate
     */
    public Coord(int setX, int setY) {
        x = setX;
        y = setY;
    }

    /**
     * Parameterized constructor
     * 
     * @param p point to make the coordinate of
     */
    public Coord(Point p) {
        x = p.x;
        y = p.y;
    }

    /**
     * Adds two coordinates
     * 
     * @param other the other operand
     * @return the result
     */
    public Coord add(Coord other) {
        return new Coord(x + other.x, y + other.y);
    }

    /**
     * Subtracts the other coordinate from itself
     * 
     * @param other the other operand
     * @return the result
     */
    public Coord sub(Coord other) {
        return new Coord(x - other.x, y - other.y);
    }

    /**
     * Multiplies the coordinates by lambda
     * 
     * @param lambda the multiplier
     * @return the result
     */
    public Coord multiply(double lambda) {
        return new Coord((int) (x * lambda), (int) (y * lambda));
    }
}
