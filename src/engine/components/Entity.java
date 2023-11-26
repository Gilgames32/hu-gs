package engine.components;

import java.util.List;
import java.util.LinkedList;

import engine.GameObject;
import scene.World;
import util.Rectangle;
import window.Window;

/**
 * Entities are dinamically bound to windows
 * and will forbid window drags if an entity is invalid
 * invalid = inside multiple windows simultaneously
 */
public class Entity extends GameComponent {
    // last panel it was inside of
    public GameObject lastValidPanel = null;

    // every invalid entity in the game
    public static List<Entity> invalidEntities = new LinkedList<>();

    // list of windows the entity is inside of
    public List<Window> inWindows = new LinkedList<>();

    @Override
    public void update() {
        recalcParent();
        recalcWindows();
    }

    /**
     * Sets lastValidPanel to the last panel it was inside of
     */
    void recalcParent() {
        for (Window window : World.windows) {
            if (window.panel.rect.isCoordInside(gameObject.position)) {
                lastValidPanel = window.panel.gameObject;
                break;
            }
        }
    }

    /**
     * Recalculates the windows the entity is currently inside of
     */
    void recalcWindows() {
        // reset list
        inWindows.clear();

        // absolute position rectangle
        Rectangle absRect = gameObject.getComponent(Transform.class).getAbsoluteRectangle();

        // check overlaps
        for (Window window : World.windows) {
            if (absRect.overlap(window.panel.rect) != null) {
                inWindows.add(window);
            }
        }

        // set validity
        if (inWindows.size() >= 2) {
            if (!invalidEntities.contains(this)) {
                invalidEntities.add(this);
            }
        } else {
            invalidEntities.remove(this);
        }
    }
}
