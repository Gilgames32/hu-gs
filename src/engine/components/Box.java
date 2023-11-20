package engine.components;

import java.awt.Color;
import java.awt.Graphics;

import util.Coord;
import util.Rectangle;

public class Box extends GameComponent {
    public Color color = Color.BLACK;
    Transform transform = null;

    public Box() {}

    public Box(Color c) {
        color = c;
    }
    
    @Override
    public void start() {
        transform = gameObject.getComponent(Transform.class);
    }

    @Override
    public void draw(Graphics g, Coord offset) {
        g.setColor(color);
        if (transform.rect != null) {
            Rectangle relativeRect = transform.rect.addPos(offset);
            g.fillRect(relativeRect.getX1(), relativeRect.getY1(), relativeRect.getSizeX(), relativeRect.getSizeY());
        }
    }
}
