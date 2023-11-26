package scene;

import engine.GameObject;
import engine.components.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

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
        cake.addComponent(new Transform(20, 16));
        cake.addComponent(new Sprite("./assets/thecakeisalie.png"));
        cake.addComponent(new Cake());
        return cake;
    }

    public static GameObject platformPrefab(int x, int y, int sizex, int sizey, GameObject parent) {
        GameObject platform = new GameObject(x, y, parent);
        platform.addComponent(new Transform(sizex, sizey, 0, 0));
        platform.addComponent(new BoxCollider());
        platform.addComponent(new Sprite("./assets/platform.png"));
        return platform;
    }

    public static GameObject imagePrefab(int x, int y, String fileName, GameObject parent) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        GameObject platform = new GameObject(x, y, parent);
        platform.addComponent(new Transform(img.getWidth(), img.getHeight(), 0, 0));
        platform.addComponent(new Sprite(img));
        return platform;
    }
}
