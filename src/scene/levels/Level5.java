package scene.levels;

import engine.GameObject;
import engine.components.SideBound.WindowSide;
import scene.Prefabs;
import scene.World;
import util.Rectangle;
import window.Window;

public class Level5 extends World {
    public Level5() {
        name = "Level5";
    }

    @Override
    public void start() {

        // TODO: hide easter egg when oob
        
        Window w1 = new Window("▇▅▆▇▆▅▅█", new Rectangle(200, 0, 275, 300));
        Window w2 = new Window("Entrance hidden by terrible Swing UI", new Rectangle(-200, 0, 400, 300));

        Prefabs.imagePrefab(-55, -100, "./assets/l5_bg.png", w1.panel.gameObject);

        w1.panel.bounds.get(WindowSide.south).freeze();
        w1.panel.bounds.get(WindowSide.west).freeze();
        w1.panel.bounds.get(WindowSide.east).freeze();

        Prefabs.platformPrefab(0, 300 - 24, 400, 24, w2.panel.gameObject);
        Prefabs.platformPrefab(280, 160, 100, 24, w1.panel.gameObject);

        GameObject player = Prefabs.playerPrefab(100, 250, root);
        player.position = player.position.add(w2.panel.rect.toCoord());

        Prefabs.cakePrefab(200, 25, w2.panel.gameObject);

        super.start();
    }
}
