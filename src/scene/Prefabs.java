package scene;

import engine.GameObject;
import engine.components.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Prefabs {

    /**
     * Private constructor, so it cannot be instantiated
     */
    Prefabs() {
    }

    /**
     * Generates the typical player GameObject
     * 
     * @param x      initial x position
     * @param y      initial y position
     * @param parent parent GameObject
     * @return the player GameObject
     */
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

    /**
     * Generates the typical cake GameObject
     * 
     * @param x      x position
     * @param y      y position
     * @param parent parent GameObject
     * @return the cake GameObject
     */
    public static GameObject cakePrefab(int x, int y, GameObject parent) {
        GameObject cake = new GameObject(x, y, parent);
        cake.addComponent(new Transform(20, 16));
        cake.addComponent(new Sprite("./assets/thecakeisalie.png"));
        cake.addComponent(new Cake());
        return cake;
    }

    /**
     * Generates the typical platform GameObject
     * 
     * @param x      x position
     * @param y      y position
     * @param sizex  width
     * @param sizey  height
     * @param parent parent GameObject
     * @return the platform GameObject
     */
    public static GameObject platformPrefab(int x, int y, int sizex, int sizey, GameObject parent) {
        GameObject platform = new GameObject(x, y, parent);
        platform.addComponent(new Transform(sizex, sizey, 0, 0));
        platform.addComponent(new BoxCollider());
        platform.addComponent(new Sprite("./assets/platform.png"));
        return platform;
    }

    /**
     * Generates the typical GameObject with just a sprite
     * 
     * @param x        x position
     * @param y        y position
     * @param filePath path to the image file
     * @param parent   parent GameObject
     * @return the sprite's GameObject
     */
    public static GameObject imagePrefab(int x, int y, String filePath, GameObject parent) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameObject platform = new GameObject(x, y, parent);
        platform.addComponent(new Transform(img.getWidth(), img.getHeight(), 0, 0));
        platform.addComponent(new Sprite(img));
        return platform;
    }

    public static GameObject trampolinePrefab(int x, int y, int sizex, int sizey, GameObject parent) {
        GameObject platform = new GameObject(x, y, parent);
        platform.addComponent(new Transform(sizex, sizey, 0, 0));
        platform.addComponent(new Trampoline());
        platform.addComponent(new Sprite("./assets/trampoline.png"));
        return platform;
    }
}
