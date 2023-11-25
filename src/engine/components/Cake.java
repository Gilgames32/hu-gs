package engine.components;

import util.Rectangle;

import engine.GameObject;
import scene.App;
import scene.World;

public class Cake extends GameComponent {
    @Override
    public void update() {
        for (GameObject other : World.root.getAllChildren()) {
            // when no nesting
            if (other.transform == null) {
                continue;
            }
            Rectangle overlapRect = gameObject.transform.getAbsoluteRectangle().overlap(other.transform.getAbsoluteRectangle());
            if (overlapRect == null) {
                continue;                
            }
            if (other.getComponent(Player.class) == null) {
                continue;
            }
            App.menu.onLevelComplete();
        }
    }
}
