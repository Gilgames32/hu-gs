package engine.components;
import java.awt.Color;

import engine.listeners.CollisionListener;
import scene.World;
import util.*;

public class Player extends GameComponent implements CollisionListener {
    double speed = 5;
    boolean canJump = true;

    Transform transform;
    BoxCollider collider;
    Rigidbody rigidbody;
    Box box;

    @Override
    public void start() {
        transform = gameObject.getComponent(Transform.class);
        collider = gameObject.getComponent(BoxCollider.class);
        rigidbody = gameObject.getComponent(Rigidbody.class);
        rigidbody.addCollisionListener(this);
        box = gameObject.getComponent(Box.class);
    }


    @Override
    public void update() {
        // inWindows();
        
        rigidbody.xVel = World.keyboard.getAxisX() * speed;
        // jump
        if (World.keyboard.getSpace() && canJump) {
            rigidbody.yVel = 10;
            canJump = false;
        }

        // stuff
        Rectangle windowRect = World.windows.get(0).panel.rect;
        if (windowRect.isRectangleInside(transform.getAbsoluteRectangle())) {
            box.color = Color.RED;
        } else {
            box.color = Color.BLUE;
        }
    }

    @Override
    public void onLand() {
        canJump = true;
    }
}
