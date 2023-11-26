package util;

import java.awt.*;

public class Rectangle {
    int x;
    int y;
    int sizeX;
    int sizeY;

    public Rectangle(int upperRightX, int upperRightY, int width, int height) {
        x = upperRightX;
        y = upperRightY;
        sizeX = width;
        sizeY = height;
    }

    public Rectangle(Point upperRight, Dimension size) {
        x = upperRight.x;
        y = upperRight.y;
        sizeX = size.width;
        sizeY = size.height;
    }

    public Rectangle(Rectangle r) {
        x = r.x;
        y = r.y;
        sizeX = r.sizeX;
        sizeY = r.sizeY;
    }

    public int getX1() {
        return x;
    }

    public int getY1() {
        return y;
    }

    public int getX2() {
        return x + sizeX;
    }

    public int getY2() {
        return y + sizeY;
    }

    public void setX1(int upperRightX) {
        x = upperRightX;
    }

    public void setY1(int upperRightY) {
        y = upperRightY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeX(int width) {
        sizeX = width;
    }

    public void setSizeY(int height) {
        sizeY = height;
    }

    public Coord toCoord() {
        return new Coord(x, y);
    }

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

    // subtract
    public Rectangle subPos(Rectangle other) {
        return new Rectangle(getX1() - other.getX1(), getY1() - other.getY1(), sizeX, sizeY);
    }

    public Rectangle subPos(Coord other) {
        return new Rectangle(getX1() - other.x, getY1() - other.y, sizeX, sizeY);
    }

    public Rectangle subPos(int subX, int subY) {
        return new Rectangle(getX1() - subX, getY1() - subY, sizeX, sizeY);
    }

    // add
    public Rectangle addPos(Rectangle other) {
        return new Rectangle(getX1() + other.getX1(), getY1() + other.getY1(), sizeX, sizeY);
    }

    public Rectangle addPos(Coord other) {
        return new Rectangle(getX1() + other.x, getY1() + other.y, sizeX, sizeY);
    }

    public Rectangle addPos(int addX, int addY) {
        return new Rectangle(getX1() + addX, getY1() + addY, sizeX, sizeY);
    }

    public boolean isPointInside(Point p) {
        return p.x > getX1() && p.x < getX2() && p.y > getY1() && p.y < getY2();
    }

    public boolean isPointInside(int x, int y) {
        return x > getX1() && x < getX2() && y > getY1() && y < getY2();
    }

    public boolean isCoordInside(Coord c) {
        return c.x > getX1() && c.x < getX2() && c.y > getY1() && c.y < getY2();
    }

    public boolean isRectangleInside(Rectangle rect) {
        return isPointInside(rect.getX1(), rect.getY1()) && isPointInside(rect.getX2(), rect.getY2());
    }
}
