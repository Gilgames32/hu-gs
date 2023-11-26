package scene.levels;

import engine.GameObject;
import engine.components.Box;
import engine.components.BoxCollider;
import engine.components.Transform;
import scene.Prefabs;
import scene.World;
import util.Rectangle;
import window.Window;
import java.awt.Color;
import engine.components.SideBound.WindowSide;

public class Level4 extends World {
    public Level4() {
        name = "Level4";
    }

    @Override
    public void start() {
        Window w1 = new Window(">w<", new Rectangle(-100, 0, 400, 300));

        GameObject player = Prefabs.playerPrefab(100, 200, root);
        player.position = player.position.add(w1.panel.rect.toCoord());

        GameObject wall = new GameObject(200, 150, w1.panel.gameObject);
        wall.addComponent(new Transform(10, 300));
        wall.addComponent(new BoxCollider());
        wall.addComponent(new Box(Color.GRAY));

        w1.panel.bounds.get(WindowSide.NORTH).freeze();
        w1.panel.bounds.get(WindowSide.SOUTH).freeze();

        Prefabs.cakePrefab(300, 250, w1.panel.gameObject);

        Window w2 = new Window("UwU", new Rectangle(300, 100, 200, 150));
        w2.panel.bounds.get(WindowSide.NORTH).freeze();
        w2.panel.bounds.get(WindowSide.WEST).freeze();
        w2.panel.bounds.get(WindowSide.EAST).freeze();

        Window w3 = new Window("OwO", new Rectangle(300, -100, 200, 150));
        w3.panel.bounds.get(WindowSide.SOUTH).freeze();
        wall = new GameObject(100, 75, w3.panel.gameObject);
        wall.addComponent(new Transform(10, 150));
        wall.addComponent(new BoxCollider());
        wall.addComponent(new Box(Color.GRAY));

        super.start();
    }
}
