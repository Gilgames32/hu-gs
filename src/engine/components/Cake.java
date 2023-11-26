package engine.components;

import engine.GameObject;
import scene.App;

public class Cake extends BoxCollider {

    public Cake() {
        isTrigger = true;
    }

    @Override
    public void onTrigger(GameObject other) {
        if (other.getComponent(Player.class) != null) {
            App.menu.onLevelComplete();
        }
    }
}
