package engine.listeners;

import engine.GameObject;

public interface CollisionListener {
    /**
     * The abstract method for landing collisions
     */
    void onLand();

    /**
     * The abstract method for collisions
     * 
     * @param other
     */
    void onCollision(GameObject other);
}
