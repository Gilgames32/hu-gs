package scene.levels;

import engine.GameObject;
import scene.Prefabs;
import scene.World;
import util.Rectangle;
import window.Window;

/**
 * Implementation of Level 2
 */
public class Level2 extends World {
    public Level2() {
        name = "2 - Chef";
    }

    @Override
    public void start() {
        Window w1 = new Window("cheffs kiss", new Rectangle(-400, 0, 400, 300));
        Window w2 = new Window("do they really?", new Rectangle(400, 0, 400, 300));

        Prefabs.imagePrefab(225, -100, "l2_bg.png", w1.panel.gameObject);

        GameObject player = Prefabs.playerPrefab(200, 150, root);
        player.position = player.position.add(w1.panel.rect.toCoord());

        Prefabs.platformPrefab(0, 300 - 24, 400, 24, w1.panel.gameObject);
        Prefabs.platformPrefab(0, 300 - 24, 400, 24, w2.panel.gameObject);

        Prefabs.cakePrefab(200, 250, w2.panel.gameObject);

        super.start();
    }
}
