package scene.levels;

import engine.GameObject;
import engine.components.*;
import scene.World;

import java.awt.Color;
import util.Rectangle;
import window.Window;

public class Level1 extends World {
    public Level1() {
        name = "Level1";
    }

    @Override
    public void start() {
        windows.add(new Window("UwU", new Rectangle(100, 100, 400, 300)));
        windows.add(new Window("OwO", new Rectangle(100 + 400, 100, 400, 300)));
        windows.add(new Window(">w<", new Rectangle(100, 100 + 300, 200, 150)));
        
        GameObject player = new GameObject(300, 200, root);
        player.addComponent(new Transform(50, 50));
        player.addComponent(new BoxCollider());
        player.addComponent(new Box(Color.MAGENTA));
        player.addComponent(new Player());
        player.addComponent(new Rigidbody());
        player.addComponent(new Entity());
        
        GameObject box = new GameObject(100, 100, windows.get(0).panel.gameObject);
        box.addComponent(new Transform(400, 50, 0, 0));
        box.addComponent(new BoxCollider());
        box.addComponent(new Box());


        super.start();
    }
}
