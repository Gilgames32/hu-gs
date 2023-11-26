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
            for (GameObject other : World.root.getAllChildren()) {
                // when no nesting
                if (other.transform == null) {
                    continue;
                }
                Rectangle overlapRect = gameObject.transform.getAbsoluteRectangle()
                        .overlap(other.transform.getAbsoluteRectangle());
                if (overlapRect != null) {
                    onTrigger(other);
                }
            }
        }
    }

    public void onTrigger(GameObject other) {

    }
}
