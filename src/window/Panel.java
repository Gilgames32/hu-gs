package window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JPanel;

import engine.GameObject;
import engine.components.Entity;
import engine.components.Intersection;
import engine.components.SideBound;
import engine.components.Transform;
import engine.components.SideBound.WindowSide;
import scene.World;
import util.*;

public class Panel extends JPanel {

    public Rectangle rect;

    // storing where its currently overlapping
    ArrayList<GameObject> intersections = new ArrayList<>();

    public GameObject gameObject = null;

    // north, south, west, east
    public Map<WindowSide, SideBound> bounds = new EnumMap<>(WindowSide.class);

    public Panel(Rectangle initRect) {
        rect = initRect;

        setPreferredSize(new Dimension(initRect.getSizeX(), initRect.getSizeY()));
        setDoubleBuffered(true);
        setFocusable(true);

        addKeyListener(World.keyboard);
        addMouseListener(World.mouse);

        // initialize gameobject
        gameObject = new GameObject(0, 0, World.root);

        initializeBounds();

    }

    public void onWindowDrag(Window window) {
        World.keyboard.releaseAll();

        Coord prevLocation = rect.toCoord();
        Coord nextLocation = new Coord(getLocationOnScreen());

        // move entities inside it
        for (GameObject other : World.root.getAllChildren()) {
            Entity entityComponent = other.getComponent(Entity.class);
            if (entityComponent != null && entityComponent.lastValidPanel == gameObject) {
                other.position = other.position.sub(prevLocation).add(nextLocation);
            }
        }

        // move the window
        rect.regenerate(nextLocation, getSize());
        gameObject.position = nextLocation;

        // validate, generates the overlap rectangles shown
        validatePos(window);

        // recalc side bounds (every window)
        World.recalcSideBounds();
    }

    public void validatePos(Window window) {
        intersections.clear();
        for (Window otherWindow : World.windows) {
            // dont compare to self
            if (otherWindow == window) {
                continue;
            }
            otherWindow.panel.intersections.clear();

            // overlapping rectangle
            Rectangle overlapRect = rect.overlap(otherWindow.panel.rect);
            if (overlapRect != null) {
                GameObject isec = new GameObject(0, 0, null);
                isec.addComponent(new Intersection(overlapRect, window));
                intersections.add(isec);
            }
        }
    }

    public boolean isOverlapping(Window window) {
        for (Window otherWindow : World.windows) {
            // dont compare to self
            if (otherWindow == window) {
                continue;
            }
            // overlapping rectangle
            Rectangle overlapRect = rect.overlap(otherWindow.panel.rect);
            if (overlapRect != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        // parent
        super.paintComponent(g);

        // itself and children
        for (GameObject child : World.root.children) {
            if (gameObject == child) {
                for (GameObject ownChild : gameObject.children) {
                    ownChild.draw(g, new Coord(0, 0));
                }
            } else {
                child.draw(g, rect.toCoord().multiply(-1));
            }
        }

        // intersections
        for (GameObject intersection : intersections) {
            intersection.draw(g, rect.toCoord().multiply(-1));
        }
    }

    public void mouseClicked(MouseEvent e) {
        for (GameObject isecGameObject : intersections) {
            Intersection isec = isecGameObject.getComponent(Intersection.class);
            if (isec == null) {
                continue;
            }
            if (isec.rect.subPos(rect).isPointInside(e.getPoint())) {
                isec.onClick();
            }
        }
    }

    public void initializeBounds() {
        for (WindowSide side : WindowSide.values()) {
            GameObject boundGameObject = new GameObject(0, 0, gameObject);
            boundGameObject.addComponent(new Transform(0, 0));
            boundGameObject.addComponent(new SideBound(this.rect, side));
            bounds.put(side, boundGameObject.getComponent(SideBound.class));
        }
    }

    public void recalcBounds() {
        for (SideBound side : bounds.values()) {
            side.recalc();
        }
    }

}
