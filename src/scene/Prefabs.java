package scene;

import engine.GameObject;
import engine.components.*;

public class Prefabs {
    public static GameObject playerPrefab(int x, int y, GameObject parent) {
        GameObject player = new GameObject(x, y, parent);
        player.addComponent(new Transform(34, 22));
        player.addComponent(new BoxCollider());
        player.addComponent(new Player());
        player.addComponent(new Rigidbody());
        player.addComponent(new Entity());
        player.addComponent(new Sprite("./assets/robko.png"));
        return player;
    }

    public static GameObject cakePrefab(int x, int y, GameObject parent) {
        GameObject cake = new GameObject(x, y, parent);
        cake.addComponent(new Transform(15, 18));
        cake.addComponent(new Sprite("./assets/andrewcake.png"));
        cake.addComponent(new Cake());
        return cake;
    }
}
