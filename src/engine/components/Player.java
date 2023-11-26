package engine.components;

import engine.GameObject;
import engine.listeners.CollisionListener;
import scene.World;

public class Player extends GameComponent implements CollisionListener {
    // movements speed
    static final double SPEED = 5;
    // jumping force
    static final double JUMPFORCE = 12;
    boolean canJump = true;

    Rigidbody rigidbody;
    Sprite sprite;

    @Override
    public void start() {
        rigidbody = gameObject.getComponent(Rigidbody.class);
        rigidbody.addCollisionListener(this);
        sprite = gameObject.getComponent(Sprite.class);
    }

    @Override
    public void update() {
        // movement
        int kbx = World.keyboard.getAxisX();
        rigidbody.xVel = kbx * SPEED;

        // jumping
        if ((World.keyboard.getSpace() || World.keyboard.getAxisY() > 0) && canJump) {
            rigidbody.yVel = JUMPFORCE;
            canJump = false;
        }

        // flipping the sprite
        if (kbx > 0) {
            sprite.flip = false;
        } else if (kbx < 0) {
            sprite.flip = true;
        }
    }

    @Override
    public void onLand() {
        canJump = true;
    }

    @Override
    public void onCollision(GameObject other) {
        // pass
    }
}
