import java.util.ArrayList;
import java.awt.Toolkit;

public class World implements Runnable {
    static ArrayList<Window> windows = new ArrayList<Window>();
    Thread gameThread;
    static KeyHandler input = new KeyHandler();
    static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public void start() {
        // start stuff here idk
        // for now we use this for initialization
        windows.add(new Window("UWU", new Rectangle(1000, 600, 800, 600)));
        gameObjects.add(new Player());




        // initalize gameobjects
        for (GameObject gameObject : gameObjects) {
            gameObject.start();
        }

        // yay multithreading
        gameThread = new Thread(this);
        // this will call run
        gameThread.start();
    }

    public void run() {
        // delta variables
        long lastTime = System.nanoTime();
        long currentTime;
        double drawInterwal = 1e9 / 90;
        double delta = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            // this way delta is a 0-1 scale indicating how far we are till the next update
            delta += (currentTime - lastTime) / drawInterwal;
            lastTime = currentTime;

            // whenthe scale is over 1 we can call it a frame
            if (delta > 1) {
                delta--;              
                // update
                update();
                // redraw
                redraw();
                
                // the 1 line of code that took me 6 hours to debug:
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }

    public void update(){
        // update
        // update should follow the top down hierarchy
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    public void redraw() {
        // repaint components
        for (Window window : windows) {
            window.frame.repaint();
        }
    }
}