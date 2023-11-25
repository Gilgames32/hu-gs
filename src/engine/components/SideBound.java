package engine.components;

import java.awt.Color;
import java.util.List;

import engine.GameObject;
import scene.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import util.Rectangle;
import window.Window;

public class SideBound extends GameComponent {
    public enum WindowSide {
        north, south, west, east
    }

    public boolean vertical;

    public WindowSide side;
    public boolean solid = false;

    private Rectangle defaultRect;
    private static final int treshold = 10;

    public SideBound(Rectangle windowRect, WindowSide windowSide) {
        side = windowSide;
        switch (windowSide) {
            case north:
                defaultRect = new Rectangle(0, -treshold, windowRect.getSizeX(), treshold);
                break;

            case south:
                defaultRect = new Rectangle(0, windowRect.getSizeY(), windowRect.getSizeX(), treshold);
                break;

            case west:
                defaultRect = new Rectangle(-treshold, 0, treshold, windowRect.getSizeY());
                break;

            case east:
                defaultRect = new Rectangle(windowRect.getSizeX(), 0, treshold, windowRect.getSizeY());
                break;

            default:
                break;
        }

        vertical = windowSide == WindowSide.east ||windowSide == WindowSide.west;
    }

    @Override
    public void start() {
        gameObject.transform.rect = defaultRect;
    }

    public void freeze() {
        solid = true;
        switch (side) {
            case north:
                defaultRect.setY1(defaultRect.getY1() + treshold);
                break;

            case south:
                defaultRect.setY1(defaultRect.getY1() - treshold);
                break;

            case west:
                defaultRect.setX1(defaultRect.getX1() + treshold);
                break;

            case east:
                defaultRect.setX1(defaultRect.getX1() - treshold);
                break;

            default:
                break;
        }
        gameObject.transform.rect = defaultRect;
        makeChild(0, vertical ? defaultRect.getSizeY() : defaultRect.getSizeX());
        Box box = gameObject.getComponent(Box.class);
        if (box != null) {
            box.color = Color.BLACK;
        }
        else {
            gameObject.addComponent(new Box(Color.BLACK));
        }
    }

    public void recalc() {
        if (solid) {
            return;
        }

        List<Rectangle> overlapList = new ArrayList<>();

        for (Window window : World.windows) {
            Rectangle overlapRect = gameObject.transform.getAbsoluteRectangle().overlap(window.panel.rect);
            if (overlapRect != null) {
                overlapList.add(overlapRect.subPos(gameObject.transform.getAbsoluteRectangle()));
            }
        }

        // gameObject.getComponent(Box.class).color = overlapList.isEmpty() ? Color.BLUE
        // : Color.RED;
        // boxCollider.isTrigger = !overlapList.isEmpty();
        // TODO: use multiple boxcolliders as children (child labor)

        // sort rectangles by x1
        Collections.sort(overlapList, Comparator.comparing(Rectangle::getX1));

        // reset children
        gameObject.destroyAllChildren();

        // 0 tól
        // első x1ig
        // create
        // kövi
        // x2től kövi x1ig
        // ha negatív akkor nem
        // kövi
        // hacsak nem mennénk size fölé
        Iterator<Rectangle> iter = overlapList.iterator();

        int i1 = 0;
        int i2 = 0;
        int max;
        if (vertical) {
            max = gameObject.transform.rect.getY2();
            while (iter.hasNext()) {
                Rectangle current = iter.next();
                i2 = current.getY1();

                // if i1 is oob
                if (i1 >= max) {
                    break;
                }
                // if i2 is oob
                if (i2 > max) {
                    i2 = max;
                }

                // create
                makeChild(i1, i2);

                i1 = current.getY2();
            }
            // the final piece, might be the only one
            makeChild(i1, max);
        } else {
            max = gameObject.transform.rect.getX2();
            while (iter.hasNext()) {
                Rectangle current = iter.next();
                i2 = current.getX1();

                // if i1 is oob
                if (i1 >= max) {
                    break;
                }
                // if i2 is oob
                if (i2 > max) {
                    i2 = max;
                }

                // create
                makeChild(i1, i2);

                i1 = current.getX2();
            }
            // the final piece, might be the only one
            makeChild(i1, max);
        }
    }

    void makeChild(int i1, int i2) {
        // check validity
        if (i1 < i2) {
            GameObject collider = new GameObject(0, 0, gameObject);
            Rectangle rect;
            if (vertical) {
                rect = new Rectangle(0, i1, 10, i2 - i1);
            } else {
                rect = new Rectangle(i1, 0, i2 - i1, 10);
            }
            collider.addComponent(new Transform(rect.addPos(gameObject.transform.rect)));
            collider.addComponent(new BoxCollider());
            collider.start();
        }
    }
}