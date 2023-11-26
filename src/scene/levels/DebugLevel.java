package scene.levels;

import engine.GameObject;
import engine.components.*;
import scene.Prefabs;
import scene.World;

import util.Rectangle;
import window.Window;

public class DebugLevel extends World {
    public DebugLevel() {
        name = "DEBUG";
    }

    @Override
    public void start() {
        windows.add(new Window("UwU", new Rectangle(100, 100, 400, 300)));
        windows.add(new Window("OwO", new Rectangle(100 + 400, 100, 400, 300)));
        windows.add(new Window(">w<", new Rectangle(100, 100 + 300, 200, 150)));
        
        Prefabs.playerPrefab(300, 200, root);
        Prefabs.cakePrefab(200, 500, root);
        
        GameObject box = new GameObject(100, 100, windows.get(0).panel.gameObject);
        box.addComponent(new Transform(400, 50, 0, 0));
        box.addComponent(new BoxCollider());
        box.addComponent(new Box());


        super.start();
    }
}
