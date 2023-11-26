package engine.components;

import java.awt.Color;
import java.awt.Graphics;

import util.Coord;
import util.Rectangle;

public class Box extends GameComponent {
    public Color color = Color.MAGENTA;

    /**
     * Default constructor
     */
    public Box() {
    }

    /**
     * Parameterized constructor
     * 
     * @param c color
     */
    public Box(Color c) {
        color = c;
    }

    @Override
    public void draw(Graphics g, Coord offset) {
        // validity check
        if (gameObject.transform == null || gameObject.transform.rect == null) {
            return;
        }
        // drawing
        g.setColor(color);
        Rectangle relativeRect = gameObject.transform.rect.addPos(offset);
        g.fillRect(relativeRect.getX1(), relativeRect.getY1(), relativeRect.getSizeX(), relativeRect.getSizeY());
    }
}
