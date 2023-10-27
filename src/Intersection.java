import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Intersection extends GameObject {
    Window parentWindow;
    
    public Intersection(Rectangle initRect, Window window) {
        rect = initRect;
        parentWindow = window;
    }

    @Override
    void draw(Graphics g, Rectangle windowPosition) {
        g.setColor(Color.CYAN);
        super.draw(g, windowPosition);
    }

    void onClick(){
        // BUG BELOW or idk cant find out why windows get placed above the actual y coordinate

        // move it wherever its closer
        if (rect.sizeX > rect.sizeY) {
            Point pos = parentWindow.frame.getLocation();
            // offset depending on direction
            pos.y += rect.getY1() == parentWindow.panel.rect.getY1() ? rect.sizeY : -rect.sizeY;
            // epic fix xd
            // pos.y += 18;
            parentWindow.frame.setLocation(pos);
        } else {
            Point pos = parentWindow.frame.getLocation();
            // offset depending on direction
            pos.x += rect.getX1() == parentWindow.panel.rect.getX1() ? rect.sizeX : -rect.sizeX;
            parentWindow.frame.setLocation(pos);
        }
    }

    
}
