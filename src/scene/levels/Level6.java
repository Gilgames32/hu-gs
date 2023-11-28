package scene.levels;

import java.awt.Color;

import engine.GameObject;
import engine.components.Box;
import engine.components.BoxCollider;
import engine.components.SideBound.WindowSide;
import engine.components.Transform;
import scene.Prefabs;
import scene.World;
import util.Rectangle;
import window.Window;

public class Level6 extends World {
    public Level6() {
        name = "6 - Siso";
    }

    @Override
    public void start() {

        Window w1 = new Window("is", new Rectangle(0, 0, 400, 300));
        w1.panel.bounds.get(WindowSide.NORTH).freeze();

        Prefabs.platformPrefab(300, 250, 100, 24, w1.panel.gameObject);

        GameObject wall = new GameObject(0, 0, w1.panel.gameObject);
        wall.addComponent(new Transform(10, 200, 0, 0));
        wall.addComponent(new BoxCollider());
        wall.addComponent(new Box(Color.GRAY));

        GameObject player = Prefabs.playerPrefab(100, 200, root);
        player.position = player.position.add(w1.panel.rect.toCoord());

        Prefabs.cakePrefab(25, 25, w1.panel.gameObject);

        // Window w2 = 
        new Window("the fog", new Rectangle(-400, 0, 400, 200));

        Window w3 = new Window("coming", new Rectangle(400, 0, 400, 200));
        Prefabs.trampolinePrefab(0, 200 - 24, 400, 24, w3.panel.gameObject);

        super.start();
    }
}
