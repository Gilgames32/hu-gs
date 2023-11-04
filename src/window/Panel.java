package window;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

import engine.GameObject;
import engine.components.Intersection;
import game.World;
import util.*;

public class Panel extends JPanel {
    // some data structure for stuff to draw:
    // probably gotta make one small list for global stuff like player
    // and a local stuff like a tilemap or some
    public Rectangle rect;

    // storing where its currently overlapping
    ArrayList<GameObject> intersections = new ArrayList<>();

    public Panel(Rectangle initRect) {
        rect = initRect;

        setPreferredSize(new Dimension(initRect.getSizeX(), initRect.getSizeY()));
        setDoubleBuffered(true);
        setFocusable(true);

        addKeyListener(World.keyboard);
        addMouseListener(World.mouse);
    }

    public void onWindowDrag(Window window) {
        World.keyboard.releaseAll();
        rect.regenerate(getLocationOnScreen(), getSize());

        // validate, generates the overlap rectangles shown
        validatePos(window);
    }

    public void validatePos(Window window) {
        intersections.clear();
        for (Window otherWindow : World.windows) {
            // dont compare to self
            if (otherWindow == window) {
                continue;
            }
            otherWindow.panel.intersections.clear();

            // todo:
            // check who its connected with and where
            // giving us the info where we can remove wallhitbox
            // thus letting the player move between screens

            // overlapping rectangle
            Rectangle overlapRect = rect.overlap(otherWindow.panel.rect);

            // ignore no overlaps
            if (overlapRect == null) {
                continue;
            } else {
                GameObject isec = new GameObject(0, 0);
                isec.addComponent(new Intersection(overlapRect, window));
            }

        }

    }

    public boolean isOverlapping() {
        return intersections.size() == 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        // parent
        super.paintComponent(g);

        // self
        for (GameObject intersection : intersections) {
            intersection.draw(g, rect.toCoord().multiply(-1));
        }

        // children
        // loop thru stuff and use their draw
        for (GameObject gameObject : World.gameObjects) {
            gameObject.draw(g, rect.toCoord().multiply(-1));
        }
    }

    public void mouseClicked(MouseEvent e) {
        /*
        for (GameObject isec : intersections) {
            if (isec.rect.relativeTo(rect).isPointInside(e.getPoint())) {
                isec.onClick();
            }
        }
        */

    }

}
