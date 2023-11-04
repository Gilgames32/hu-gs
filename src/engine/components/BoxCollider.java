package engine.components;

import util.Rectangle;

public class BoxCollider extends GameComponent {
    Rectangle rect = null;
    boolean isTrigger = false;

    @Override
    public void start() {
        Transform transform = gameObject.getComponent(Transform.class);
        if (transform != null) {
            rect = transform.rect;
        }
    }
}
