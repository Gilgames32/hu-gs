package engine.components;

import engine.GameObject;
import scene.App;

public class Cake extends BoxCollider {

    /**
     * Default constructor
     */
    public Cake() {
        isTrigger = true;
    }

    @Override
    public void onTrigger(GameObject other) {
        // only the player can activate it
        if (other.getComponent(Player.class) != null) {
            App.menu.onLevelComplete();
        }
    }
}
