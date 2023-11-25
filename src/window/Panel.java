package window;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

import engine.GameObject;
import engine.components.Entity;
import engine.components.Intersection;
import engine.components.SideBound;
import engine.components.Transform;
import scene.World;
import util.*;

public class Panel extends JPanel {
    // some data structure for stuff to draw:
    // probably gotta make one small list for global stuff like player
    // and a local stuff like a tilemap or some
    public Rectangle rect;

    // storing where its currently overlapping
    ArrayList<GameObject> intersections = new ArrayList<>();

    public GameObject gameObject = null;

    // north, south, west, east
    public SideBound[] bounds = new SideBound[4];

    public Panel(Rectangle initRect) {
        rect = initRect;

        setPreferredSize(new Dimension(initRect.getSizeX(), initRect.getSizeY()));
        setDoubleBuffered(true);
        setFocusable(true);

        addKeyListener(World.keyboard);
        addMouseListener(World.mouse);

        // initialize gameobject
        gameObject = new GameObject(0, 0, World.root);
        
        initializeBounds(initRect);

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
                // TODO: stinky code
                GameObject isec = new GameObject(0, 0, null);
                isec.addComponent(new Intersection(overlapRect, window));
                intersections.add(isec);
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

        // intersections
        for (GameObject intersection : intersections) {
            intersection.draw(g, rect.toCoord().multiply(-1));
        }

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
    }

    public void mouseClicked(MouseEvent e) {
        for (GameObject gameObject : intersections) {
            Intersection isec = gameObject.getComponent(Intersection.class);
            if (isec == null) { continue; }
            if (isec.rect.subPos(rect).isPointInside(e.getPoint())) {
                isec.onClick();
            }
        }

    }

    public void initializeBounds(Rectangle initRect) {
        // this is stupid bad code but runs
        GameObject[] boundGameObjects = new GameObject[4];
        for (int i = 0; i < boundGameObjects.length; i++) {
            boundGameObjects[i] = new GameObject(0, 0, gameObject);
        }

        int treshold = 10;
        boundGameObjects[0].addComponent(new Transform(initRect.getSizeX(), treshold, 0, -treshold));
        boundGameObjects[1].addComponent(new Transform(initRect.getSizeX(), treshold, 0, initRect.getSizeY()));
        boundGameObjects[2].addComponent(new Transform(treshold, initRect.getSizeY(), -treshold, 0));
        boundGameObjects[3].addComponent(new Transform(treshold, initRect.getSizeY(), initRect.getSizeX(), 0));

        for (GameObject bound : boundGameObjects) {
            // bound.addComponent(new Box());
            // bound.addComponent(new BoxCollider());
            bound.addComponent(new SideBound());
        }
        for (int i = 0; i < boundGameObjects.length; i++) {
            bounds[i] = boundGameObjects[i].getComponent(SideBound.class);
            bounds[i].vertical = i >= 2;
        }
    }

    public void recalcBounds() {
        for (SideBound side : bounds) {
            side.recalc();
        }
    }

}
