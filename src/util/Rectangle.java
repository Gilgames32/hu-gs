package util;

import java.awt.*;

public class Rectangle {
    int x;
    int y;
    int sizeX;
    int sizeY;

    /**
     * Parameterized constructor
     * 
     * @param upperRightX x coordinate of the right side
     * @param upperRightY y coordinate of the upper side
     * @param width       width of the rectangle
     * @param height      height of the rectangle
     */
    public Rectangle(int upperRightX, int upperRightY, int width, int height) {
        x = upperRightX;
        y = upperRightY;
        sizeX = width;
        sizeY = height;
    }

    /**
     * Parameterized constructor
     * 
     * @param upperRight point of the upper right corner
     * @param size       dimension of the rectangle's size
     */
    public Rectangle(Point upperRight, Dimension size) {
        x = upperRight.x;
        y = upperRight.y;
        sizeX = size.width;
        sizeY = size.height;
    }

    /**
     * Getter for X1
     * 
     * @return x coordinate of the right side
     */
    public int getX1() {
        return x;
    }

    /**
     * Getter for Y1
     * 
     * @return y coordinate of the upper side
     */
    public int getY1() {
        return y;
    }

    /**
     * Getter for X2
     * 
     * @return x coordinate of the left side
     */
    public int getX2() {
        return x + sizeX;
    }

    /**
     * Getter for Y2
     * 
     * @return y coordinate of the lower side
     */
    public int getY2() {
        return y + sizeY;
    }

    /**
     * Setter for X1
     * 
     * @param upperRightX x coordinate of the right side
     */
    public void setX1(int upperRightX) {
        x = upperRightX;
    }

    /**
     * Setter for Y1
     * 
     * @param upperRightY y coordinate of the upper side
     */
    public void setY1(int upperRightY) {
        y = upperRightY;
    }

    /**
     * Getter for the width
     * 
     * @return the width
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Getter for the height
     * 
     * @return the height
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Setter for the width
     * 
     * @param width
     */
    public void setSizeX(int width) {
        sizeX = width;
    }

    /**
     * Setter for the height
     * 
     * @param height
     */
    public void setSizeY(int height) {
        sizeY = height;
    }

    /**
     * Returns the coordinates of the upper right corner
     * 
     * @return upper right coord
     */
    public Coord toCoord() {
        return new Coord(x, y);
    }

    /**
     * Generates the rectangle in which this and other are overlapping
     * 
     * @param other the other rectangle
     * @return the overlapping rectangle
     */
    public Rectangle overlap(Rectangle other) {
        int x1 = this.getX1() > other.getX1() ? this.getX1() : other.getX1();
        int x2 = this.getX2() < other.getX2() ? this.getX2() : other.getX2();
        int y1 = this.getY1() > other.getY1() ? this.getY1() : other.getY1();
        int y2 = this.getY2() < other.getY2() ? this.getY2() : other.getY2();

        Rectangle olrect = new Rectangle(x1, y1, x2 - x1, y2 - y1);

        // invalid size -> they are not overlapping
        if (olrect.sizeX <= 0 || olrect.sizeY <= 0) {
            return null;
        }

        return olrect;
    }

    /**
     * Re-initializes the values of the rectangle
     * 
     * @param upperRight upper right coordinates
     * @param size       dimensions of the size
     */
    public void regenerate(Coord upperRight, Dimension size) {
        x = upperRight.x;
        y = upperRight.y;
        sizeX = size.width;
        sizeY = size.height;
    }

    public String toString() {
        return String.format("x1: %s\ty1: %s\tx2: %s\ty2: %s\tsizex: %s\tsizey: %s", getX1(), getY1(), getX2(), getY2(),
                sizeX, sizeY);
    }

    /**
     * Subtracts the upper right coorinates of other from this
     * 
     * @param other the other rectangle
     * @return the resulting rectangle
     */
    public Rectangle subPos(Rectangle other) {
        return new Rectangle(getX1() - other.getX1(), getY1() - other.getY1(), sizeX, sizeY);
    }

    /**
     * Subtracts the coordinates from it's upper right coorinates
     * 
     * @param other the other coordinate
     * @return the resulting rectangle
     */
    public Rectangle subPos(Coord other) {
        return new Rectangle(getX1() - other.x, getY1() - other.y, sizeX, sizeY);
    }

    /**
     * Subtracts the coordinates from it's upper right coorinates
     * 
     * @param subX x coord
     * @param subY y coord
     * @return the resulting rectangle
     */
    public Rectangle subPos(int subX, int subY) {
        return new Rectangle(getX1() - subX, getY1() - subY, sizeX, sizeY);
    }

    /**
     * Adds the upper right coorinates of other to this
     * 
     * @param other the other rectangle
     * @return the resulting rectangle
     */
    public Rectangle addPos(Rectangle other) {
        return new Rectangle(getX1() + other.getX1(), getY1() + other.getY1(), sizeX, sizeY);
    }

    /**
     * Adds the coordinates to it's upper right coorinates
     * 
     * @param other the other coordinate
     * @return the resulting rectangle
     */
    public Rectangle addPos(Coord other) {
        return new Rectangle(getX1() + other.x, getY1() + other.y, sizeX, sizeY);
    }

    /**
     * Adds the coordinates to it's upper right coorinates
     * 
     * @param addX x coord
     * @param addY y coord
     * @return the resulting rectangle
     */
    public Rectangle addPos(int addX, int addY) {
        return new Rectangle(getX1() + addX, getY1() + addY, sizeX, sizeY);
    }

    /**
     * Decides if a point is inside of it
     * 
     * @param p the point
     * @return wether p is inside it
     */
    public boolean isPointInside(Point p) {
        return p.x > getX1() && p.x < getX2() && p.y > getY1() && p.y < getY2();
    }

    /**
     * Decides if a point is inside of it
     * 
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return wether p is inside it
     */
    public boolean isPointInside(int x, int y) {
        return x > getX1() && x < getX2() && y > getY1() && y < getY2();
    }

    /**
     * Decides if a coordinate is inside of it
     * 
     * @param c the coordinate
     * @return wether c is inside it
     */
    public boolean isCoordInside(Coord c) {
        return c.x > getX1() && c.x < getX2() && c.y > getY1() && c.y < getY2();
    }

    /**
     * Decides if the entirety of the other rectagnle is inside of it
     * 
     * @param other the other rectangle
     * @return wether other is fully inside it
     */
    public boolean isRectangleInside(Rectangle other) {
        return isPointInside(other.getX1(), other.getY1()) && isPointInside(other.getX2(), other.getY2());
    }
}
