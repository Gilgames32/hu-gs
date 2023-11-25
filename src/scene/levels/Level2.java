package scene.levels;
import engine.GameObject;
import engine.components.*;
import engine.components.SideBound.WindowSide;
import scene.World;
import util.Rectangle;
import window.Window;
import java.awt.Color;

public class Level2 extends World {
    public Level2() {
        name = "Level2";
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

        windows.get(0).panel.bounds.get(WindowSide.east).freeze();
        
        super.start();
    }
}
