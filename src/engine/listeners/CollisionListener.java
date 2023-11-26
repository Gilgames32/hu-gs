package engine.listeners;

import engine.GameObject;

public interface CollisionListener {
    /**
     * The abstract method for landing collisions
     */
    void onLand();

    void onCollision(GameObject other);
}
