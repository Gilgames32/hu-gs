package engine.components;

import java.util.LinkedList;
import java.util.List;

import engine.GameObject;
import engine.listeners.CollisionListener;
import scene.World;
import util.Coord;
import util.Rectangle;

/**
 * Rigidbody component for collisions
 * Uses raycasting, was not meant for multiple rigidbodies colliding
 * Pretty basic, can easily go out of bounds
 * Note: for some reason the collision dies if the size is an odd integer
 * due to the signedSizeXPer2 dividing by two I assume
 * Would break if the transform is not centered around the position
 */
public class Rigidbody extends GameComponent {
    // velocities
    double xVel = 0;
    double yVel = 0;

    // accelaration due to gravity
    double gravityScale = .5;

    private Coord prevPos;
    private BoxCollider selfCollider;

    private List<CollisionListener> listeners = new LinkedList<>();

    @Override
    public void start() {
        prevPos = gameObject.position;
        selfCollider = gameObject.getComponent(BoxCollider.class);
    }

    @Override
    public void update() {
        // apply gravity
        yVel -= gravityScale;

        // next position is keyboard inputs + velocity
        Coord nextPos = new Coord(gameObject.position.x, gameObject.position.y);

        // apply the velocity
        nextPos.x += xVel;
        nextPos.y -= yVel;

        // x collision
        if (prevPos.x != nextPos.x) {
            int diffX = nextPos.x - prevPos.x;
            // size/2 in the direction of the movement
            int signedSizeXPer2 = (selfCollider.rect.getSizeX() / 2) * (Math.signum(diffX) > 0 ? 1 : -1);
            boolean positiveX = Math.signum(diffX) > 0;

            Rectangle xRay = gameObject.getComponent(Transform.class).getAbsoluteRectangle();
            xRay.setSizeX(xRay.getSizeX() + diffX);
            if (!positiveX) {
                xRay.setX1(xRay.getX1() + diffX);
            }

            int closestXIntersection = nextPos.x + signedSizeXPer2;
            GameObject collidedWith = null;
            for (GameObject other : World.root.getAllChildren()) {
                BoxCollider collider = other.getComponent(BoxCollider.class);
                // skipping non collidables
                if (collider == null || collider.isTrigger || collider.gameObject == gameObject) {
                    continue;
                }

                // check if they intersect
                Rectangle overlapRect = xRay.overlap(other.getComponent(Transform.class).getAbsoluteRectangle());
                if (overlapRect != null) {
                    // get the closest point of intersection
                    if (Math.signum(diffX) > 0) {
                        // min
                        if (overlapRect.getX1() < closestXIntersection) {
                            closestXIntersection = overlapRect.getX1();
                            collidedWith = other;
                        }
                    } else {
                        // max
                        if (overlapRect.getX2() > closestXIntersection) {
                            closestXIntersection = overlapRect.getX2();
                            collidedWith = other;
                        }
                    }
                }
            }

            gameObject.position.x = closestXIntersection - signedSizeXPer2;
            // reset velocity when hit
            if (nextPos.x != gameObject.position.x) {
                listenersNotifyOnCollision(collidedWith);
                collidedWith.getComponent(BoxCollider.class).onCollision(gameObject);
                xVel = 0;
            }
        }

        // y collision
        if (prevPos.y != nextPos.y) {
            int diffY = nextPos.y - prevPos.y;
            // size/2 in the direction of the movement
            int signedSizeYPer2 = (selfCollider.rect.getSizeY() / 2) * (Math.signum(diffY) > 0 ? 1 : -1);
            boolean positiveY = Math.signum(diffY) > 0;

            Rectangle yRay = gameObject.getComponent(Transform.class).getAbsoluteRectangle();
            yRay.setSizeY(yRay.getSizeY() + diffY);
            if (!positiveY) {
                yRay.setY1(yRay.getY1() + diffY);
            }

            int closestYIntersection = nextPos.y + signedSizeYPer2;
            GameObject collidedWith = null;
            for (GameObject other : World.root.getAllChildren()) {
                BoxCollider collider = other.getComponent(BoxCollider.class);
                // skipping non collidables
                if (collider == null || collider.isTrigger || collider.gameObject == gameObject) {
                    continue;
                }

                // check if they intersect
                Rectangle overlapRect = yRay.overlap(other.getComponent(Transform.class).getAbsoluteRectangle());
                if (overlapRect != null) {
                    // get the closest point of intersection
                    if (Math.signum(diffY) > 0) {
                        // min
                        if (overlapRect.getY1() < closestYIntersection) {
                            closestYIntersection = overlapRect.getY1();
                            collidedWith = other;
                        }
                    } else {
                        // max
                        if (overlapRect.getY2() > closestYIntersection) {
                            closestYIntersection = overlapRect.getY2();
                            collidedWith = other;
                        }
                    }
                }
            }

            gameObject.position.y = closestYIntersection - signedSizeYPer2;
            // reset velocity when hit
            if (nextPos.y != gameObject.position.y) {
                // ghetto fix for trampolines
                double prevYVel = yVel;
                if (positiveY) {
                    listenersNotifyOnLand();
                }
                listenersNotifyOnCollision(collidedWith);
                collidedWith.getComponent(BoxCollider.class).onCollision(gameObject);
                if (yVel == prevYVel) {
                    yVel = 0;
                }
            }
        }

        // store previous position
        prevPos = gameObject.position;
    }

    /**
     * Adds a CollisionListener to the listeners
     * 
     * @param newListener the listener in question
     */
    public void addCollisionListener(CollisionListener newListener) {
        listeners.add(newListener);
    }

    /**
     * Notifies the listeners on land, calls their onLand function
     */
    void listenersNotifyOnLand() {
        for (CollisionListener listener : listeners) {
            listener.onLand();
        }
    }

    /**
     * Notifies the listeners on collision, calls their onCollision function
     */
    void listenersNotifyOnCollision(GameObject other) {
        for (CollisionListener listener : listeners) {
            listener.onCollision(other);
        }
    }
}
