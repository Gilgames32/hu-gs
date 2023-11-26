package scene.levels;

import engine.GameObject;
import scene.Prefabs;
import scene.World;
import util.Rectangle;
import window.Window;

public class Level3 extends World {
    public Level3 () {
        name = "Level3";
    }

    @Override
    public void start() {
        Window w1 = new Window("kettőspont", new Rectangle(-400, 0, 400, 300));
        Window w2 = new Window("három", new Rectangle(400, 0, 400, 300));


        GameObject player = Prefabs.playerPrefab(200, 50, root);
        player.position = player.position.add(w1.panel.rect.toCoord());

        Prefabs.platformPrefab(0, 100 - 24, 400, 24, w1.panel.gameObject);
        Prefabs.platformPrefab(0, 300 - 24, 400, 24, w2.panel.gameObject);

        Prefabs.cakePrefab(200, 20, w2.panel.gameObject);

        super.start();
    }
}
