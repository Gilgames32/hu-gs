package engine.components;
import java.awt.Color;

import game.World;
import window.Window;
import util.*;

public class Player extends GameComponent {
    double speed = 5;
    BoxCollider collider;
    Color color;

    @Override
    public void start() {
        collider = gameObject.getComponent(BoxCollider.class);
        color = gameObject.getComponent(Box.class).color;
    }


    @Override
    public void update() {
        inWindows();
        gameObject.getComponent(Rigidbody.class).xVel = World.keyboard.getAxisX() * speed;
        gameObject.getComponent(Rigidbody.class).yVel = World.keyboard.getAxisY() * speed;

        // stuff
        Rectangle windowRect = World.windows.get(0).panel.rect;
        if (windowRect.isRectangleInside(collider.rect)) {
            this.color = Color.RED;
        } else {
            this.color = Color.BLUE;
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
}
