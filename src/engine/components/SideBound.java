package engine.components;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import game.World;
import util.Rectangle;
import window.Window;

public class SideBound extends GameComponent {
    BoxCollider boxCollider;

    Rectangle defaultRect;

    @Override
    public void start() {
        boxCollider = gameObject.getComponent(BoxCollider.class);
        defaultRect = new Rectangle(gameObject.transform.rect);
    }

    public void recalc() {
        List<Rectangle> overlapList = new ArrayList<>();

        for (Window window : World.windows) {
            Rectangle overlapRect = gameObject.transform.getAbsoluteRectangle().overlap(window.panel.rect);
            if (overlapRect != null) {
                overlapList.add(overlapRect);
            }
        }

        gameObject.getComponent(Box.class).color = overlapList.isEmpty() ? Color.BLUE : Color.RED;
        boxCollider.isTrigger = !overlapList.isEmpty();
        // TODO: use mask instead
    }
}
