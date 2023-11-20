package engine.components;
import java.awt.Color;

import engine.listeners.CollisionListener;
import game.World;
import window.Window;
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
        inWindows();
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

    int inWindows() {
        int windownCount = 0;
        for (Window window : World.windows) {
            if (collider.rect.overlap(window.panel.rect) != null) {
                windownCount++;
            }
        }

        for (Window window : World.windows) {
            // this will set draggable true if its in less than two windows or the player is
            // not in that window
            window.draggable = collider.rect.overlap(window.panel.rect) == null || windownCount < 2;
        }

        return windownCount;
    }

    @Override
    public void onLand() {
        canJump = true;
    }
}
