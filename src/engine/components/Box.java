package engine.components;

import java.awt.Color;
import java.awt.Graphics;

import util.Coord;
import util.Rectangle;

public class Box extends GameComponent {
    public Color color = Color.BLACK;
    Rectangle rect;

    public Box() {}

    public Box(Color c) {
        color = c;
    }
    
    @Override
    public void start() {
        BoxCollider collider = gameObject.getComponent(BoxCollider.class);
        if (collider != null) {
            rect = collider.rect;
        }
    }

    @Override
    public void draw(Graphics g, Coord offset) {
        g.setColor(color);
        if (rect != null) {
            Rectangle relativeRect = rect.toRelative(offset);
            g.fillRect(relativeRect.getX1(), relativeRect.getY1(), relativeRect.getSizeX(), relativeRect.getSizeY());
        }
    }
}
