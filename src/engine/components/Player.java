package engine.components;

import engine.listeners.CollisionListener;
import scene.World;

public class Player extends GameComponent implements CollisionListener {
    double speed = 5;
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
        int kbx = World.keyboard.getAxisX();
        rigidbody.xVel = kbx * speed;
        // jump
        if (World.keyboard.getSpace() && canJump) {
            rigidbody.yVel = 10;
            canJump = false;
        }

        if (kbx > 0) {
            sprite.flip = false;
        }
        else if (kbx < 0) {
            sprite.flip = true;
        }
    }

    @Override
    public void onLand() {
        canJump = true;
    }
}
