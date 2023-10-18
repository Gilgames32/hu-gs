import java.awt.*;

public class Rectangle {
    int x1, x2, y1, y2;
    int sizex, sizey;
    
    public Rectangle(int x, int y, int width, int height) {
        x1 = x;
        y1 = y;
        sizex = width;
        sizey = height;
        x2 = x1 + sizex;
        y2 = y1 + sizey;
    }

    public Rectangle(Point upperRight, Dimension size) {
        x1 = upperRight.x;
        y1 = upperRight.y;
        sizex = size.width;
        sizey = size.height;
        x2 = x1 + sizex;
        y2 = y1 + sizey;
    }

    public Rectangle overlap(Rectangle other) {
        Rectangle olrect = new Rectangle(0, 0, 0, 0);
        olrect.x1 = this.x1 > other.x1 ? this.x1 : other.x1;
        olrect.x2 = this.x2 < other.x2 ? this.x2 : other.x2;
        olrect.y1 = this.y1 > other.y1 ? this.y1 : other.y1;
        olrect.y2 = this.y2 < other.y2 ? this.y2 : other.y2;

        olrect.sizex = olrect.x2 - olrect.x1;
        olrect.sizey = olrect.y2 - olrect.y1;

        // invalid size -> they are not overlapping
        if (olrect.sizex <= 0 || olrect.sizey <= 0) {
            return null;
        }

        return olrect;
    }

    public void regenerate(Point upperRight, Dimension size) {
        x1 = upperRight.x;
        y1 = upperRight.y;
        sizex = size.width;
        sizey = size.height;
        x2 = x1 + sizex;
        y2 = y1 + sizey;
    }

    public void regenerate(Rectangle other) {
        x1 = other.x1;
        y1 = other.y1;
        sizex = other.sizex;
        sizey = other.sizey;
        x2 = other.x2;
        y2 = other.y2;
    }

    public void regenerate(int x, int y, int width, int height) {
        x1 = x;
        y1 = y;
        sizex = width;
        sizey = height;
        x2 = x1 + sizex;
        y2 = y1 + sizey;
    }

    public String toString() {
        return String.format("x1: %s\ty1: %s\tx2: %s\ty2: %s\tsizex: %s\tsizey: %s", x1, y1, x2, y2, sizex, sizey);
    }

    public Rectangle relativeTo(Rectangle other) {
        return new Rectangle(x1 - other.x1, y1 - other.y1, sizex, sizey);
    }

    public boolean isPointInside(Point p) {
        return p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2;
    }
}
