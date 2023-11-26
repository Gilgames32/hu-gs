package engine.components;

import java.awt.Color;
import java.awt.Graphics;

import util.Coord;
import util.Rectangle;
import java.awt.image.ImageObserver;

public class Box extends GameComponent {
    public Color color = Color.BLACK;
    Transform transform = null;

    public Box() {
    }

    public Box(Color c) {
        color = c;
    }

    @Override
    public void start() {
        transform = gameObject.getComponent(Transform.class);
    }

    @Override
    public void draw(Graphics g, Coord offset, ImageObserver imgObs) {
        if (transform == null || transform.rect == null) {
            return;
        }
        g.setColor(color);
        Rectangle relativeRect = transform.rect.addPos(offset);
        g.fillRect(relativeRect.getX1(), relativeRect.getY1(), relativeRect.getSizeX(), relativeRect.getSizeY());
    }
}
