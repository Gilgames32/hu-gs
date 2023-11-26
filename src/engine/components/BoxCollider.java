package engine.components;

import engine.GameObject;
import scene.World;
import util.Rectangle;

public class BoxCollider extends GameComponent {
    boolean isTrigger = false;
    public Rectangle rect;

    @Override
    public void start() {
        rect = gameObject.transform.rect;
    }

    @Override
    public void update() {
        if (isTrigger) {
            // check for other
            for (GameObject other : World.root.getAllChildren()) {
                // ignore abstract
                if (other.transform == null) {
                    continue;
                }
                // ignore non-collidables
                if (other.getComponent(BoxCollider.class) == null) {
                    continue;
                }
                // get overlap
                Rectangle overlapRect = gameObject.transform.getAbsoluteRectangle()
                        .overlap(other.transform.getAbsoluteRectangle());
                // trigger if overlap
                if (overlapRect != null) {
                    onTrigger(other);
                }
            }
        }
    }

    /**
     * Unimplemented function, used in subclasses
     * 
     * @param other
     */
    public void onTrigger(GameObject other) {
    }

    public void onCollision(GameObject other) {
    }
}
