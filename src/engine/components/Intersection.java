package engine.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import util.Coord;
import util.Rectangle;
import window.Window;

public class Intersection extends GameComponent {
    Window parentWindow;
    public Rectangle rect;

    /**
     * Parameterized constructor
     * 
     * @param initRect initial rectange, position is relative to the parent window,
     *                 represents the overlapping rectangle
     * @param window   corresponding window
     */
    public Intersection(Rectangle initRect, Window window) {
        rect = initRect;
        parentWindow = window;
    }

    @Override
    public void draw(Graphics g, Coord offset) {
        g.setColor(Color.CYAN);
        if (rect != null) {
            Rectangle relativeRect = rect.addPos(offset);
            g.fillRect(relativeRect.getX1(), relativeRect.getY1(), relativeRect.getSizeX(), relativeRect.getSizeY());
        }
    }

    /**
     * Called when the component is clicked
     * Moves window to to the nearest valid position
     */
    public void onClick() {
        // known bug:
        // some (tested on cinnamon) desktop environments get the coordinates wrong

        // move it wherever its closer
        if (rect.getSizeX() > rect.getSizeY()) {
            Point pos = parentWindow.frame.getLocation();
            // offset depending on direction
            pos.y += rect.getY1() == parentWindow.panel.rect.getY1() ? rect.getSizeY() : -rect.getSizeY();
            parentWindow.frame.setLocation(pos);
        } else {
            Point pos = parentWindow.frame.getLocation();
            // offset depending on direction
            pos.x += rect.getX1() == parentWindow.panel.rect.getX1() ? rect.getSizeX() : -rect.getSizeX();
            parentWindow.frame.setLocation(pos);
        }
    }

}
