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
    // enum for the sides
    public enum WindowSide {
        NORTH, SOUTH, WEST, EAST
    }

    public boolean isVertical;
    public WindowSide side;

    public boolean solid = false;

    private Rectangle defaultRect;
    private static final int TRESHOLD = 10;

    /**
     * Parameterized constructor
     * 
     * @param windowRect the window's rectangle
     * @param windowSide which side of the window it is on
     */
    public SideBound(Rectangle windowRect, WindowSide windowSide) {
        // set its position and size depending on the window and the side
        side = windowSide;
        switch (windowSide) {
            case NORTH:
                defaultRect = new Rectangle(0, -TRESHOLD, windowRect.getSizeX(), TRESHOLD);
                break;

            case SOUTH:
                defaultRect = new Rectangle(0, windowRect.getSizeY(), windowRect.getSizeX(), TRESHOLD);
                break;

            case WEST:
                defaultRect = new Rectangle(-TRESHOLD, 0, TRESHOLD, windowRect.getSizeY());
                break;

            case EAST:
                defaultRect = new Rectangle(windowRect.getSizeX(), 0, TRESHOLD, windowRect.getSizeY());
                break;

            default:
                break;
        }

        isVertical = windowSide == WindowSide.EAST || windowSide == WindowSide.WEST;
    }

    @Override
    public void start() {
        gameObject.transform.rect = defaultRect;
    }

    /**
     * Freezes a side, recalulates it's corresponding colliders and rectangles
     * Makes a side solid
     */
    public void freeze() {
        // move it inside the window and make it solid
        solid = true;
        switch (side) {
            case NORTH:
                defaultRect.setY1(defaultRect.getY1() + TRESHOLD);
                break;

            case SOUTH:
                defaultRect.setY1(defaultRect.getY1() - TRESHOLD);
                break;

            case WEST:
                defaultRect.setX1(defaultRect.getX1() + TRESHOLD);
                break;

            case EAST:
                defaultRect.setX1(defaultRect.getX1() - TRESHOLD);
                break;

            default:
                break;
        }
        // give itself a collider
        gameObject.transform.rect = defaultRect;
        makeChild(0, isVertical ? defaultRect.getSizeY() : defaultRect.getSizeX());
        Box box = gameObject.getComponent(Box.class);
        if (box != null) {
            box.color = Color.GRAY;
        } else {
            gameObject.addComponent(new Box(Color.GRAY));
        }
    }

    /**
     * Calculates where the colliders of this side should be depending on the
     * windows overlapping with it
     */
    public void recalc() {
        // ignore solid ones
        if (solid) {
            return;
        }

        // get overlaps
        List<Rectangle> overlapList = new ArrayList<>();
        for (Window window : World.windows) {
            Rectangle overlapRect = gameObject.transform.getAbsoluteRectangle().overlap(window.panel.rect);
            if (overlapRect != null) {
                overlapList.add(overlapRect.subPos(gameObject.transform.getAbsoluteRectangle()));
            }
        }

        // sort overlap rectagnles by y1/x1 depending on verticality
        Collections.sort(overlapList, Comparator.comparing(isVertical ? Rectangle::getY1 : Rectangle::getX1));

        // reset children
        gameObject.destroyAllChildren();

        // fill in blanks
        // from previous y2/x2 to next y1/x1, with many many special cases
        Iterator<Rectangle> iter = overlapList.iterator();
        int i1 = 0;
        int i2 = 0;
        int max;
        if (isVertical) {
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

    /**
     * Creates a collider from i1 to i2 depending on the size, if its valid
     * 
     * @param i1 start of the collider
     * @param i2 end of the collider
     */
    void makeChild(int i1, int i2) {
        // check validity
        if (i1 < i2) {
            // make the GameObject
            GameObject collider = new GameObject(0, 0, gameObject);
            Rectangle rect;
            if (isVertical) {
                rect = new Rectangle(0, i1, 10, i2 - i1);
            } else {
                rect = new Rectangle(i1, 0, i2 - i1, 10);
            }
            collider.addComponent(new Transform(rect.addPos(gameObject.transform.rect)));
            collider.addComponent(new BoxCollider());

            // artificially start it so components don't break
            collider.start();
        }
    }
}
