import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

public class World implements Runnable {
    static ArrayList<Window> windows = new ArrayList<Window>();
    static KeyHandler keyboard = new KeyHandler();
    static MouseHandler mouse = new MouseHandler();
    static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    
    Thread gameThread;

    public void start() {
        // start stuff here idk
        // for now we use this for initialization
        windows.add(new Window("UWU", new Rectangle(1000, 600, 800, 600)));
        windows.add(new Window("OwO", new Rectangle(1000+800, 600, 800, 600)));
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
        int FPS = 90;
        // deltaTime in miliseconds
        long deltaTime = Math.round(1000/FPS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // update
                update();
                // redraw
                redraw();
                
                // the 1 line of code that took me 6 hours to debug:
                Toolkit.getDefaultToolkit().sync();
            }
        }, 0, deltaTime);
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

    public static void mouseClicked(MouseEvent e) {
        for (Window window : windows) {
            if (window.frame.isActive()) {
                window.panel.mouseClicked(e);
            }
        }
    }
}