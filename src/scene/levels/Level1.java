package scene.levels;

import engine.GameObject;
import scene.Prefabs;
import scene.World;
import util.Rectangle;
import window.Window;

/**
 * Implementation of Level 1
 */
public class Level1 extends World {
    public Level1() {
        name = "1 - Tori";
    }

    @Override
    public void start() {
        // this first so its the background
        GameObject bg = Prefabs.imagePrefab(0, -50, "./assets/l1_bg.png", root);

        Window mainWindow = new Window("WASD SPACE", new Rectangle(0, 0, 400, 300));
        GameObject main = mainWindow.panel.gameObject;

        bg.position = bg.position.add(mainWindow.panel.rect.toCoord());

        GameObject player = Prefabs.playerPrefab(200, 150, root);
        player.position = player.position.add(mainWindow.panel.rect.toCoord());

        Prefabs.platformPrefab(0, 300 - 24, 400, 24, main);
        Prefabs.platformPrefab(300, 150, 100, 24, main);
        Prefabs.platformPrefab(100, 50, 100, 24, main);

        Prefabs.cakePrefab(150, 40, main);

        super.start();
    }
}
