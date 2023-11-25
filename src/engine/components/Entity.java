package engine.components;

import java.util.List;
import java.util.LinkedList;

import engine.GameObject;
import scene.World;
import util.Rectangle;
import window.Window;

public class Entity extends GameComponent {
    public GameObject lastValidPanel = null;
    public static List<Entity> invalidEntities = new LinkedList<>();
    public List<Window> inWindows = new LinkedList<>();

    @Override
    public void update() {
        recalcParent();
        recalcWindows();
    }

    void recalcParent() {
        for (Window window : World.windows) {
            if (window.panel.rect.isCoordInside(gameObject.position)) {
                lastValidPanel = window.panel.gameObject;
            }
        }
    }

    void recalcWindows() {
        inWindows.clear();

        Rectangle absRect = gameObject.getComponent(Transform.class).getAbsoluteRectangle();

        for (Window window : World.windows) {
            if (absRect.overlap(window.panel.rect) != null) {
                inWindows.add(window);
            }
        }

        if (inWindows.size() >= 2) {
            if (!invalidEntities.contains(this)) {
                invalidEntities.add(this);
            }
        } else {
            invalidEntities.remove(this);
        }
    }
}
