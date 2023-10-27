import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class World implements Runnable {
    static ArrayList<Window> windows = new ArrayList<>();
    static KeyHandler keyboard = new KeyHandler();
    static MouseHandler mouse = new MouseHandler();
    static ArrayList<GameObject> gameObjects = new ArrayList<>();

    Thread gameThread;

    public void start() {
        // start stuff here idk
        // for now we use this for initialization
        windows.add(new Window("UWU", new Rectangle(100, 100, 400, 300)));
        windows.add(new Window("OwO", new Rectangle(100 + 400, 100, 400, 300)));
        gameObjects.add(new Player(new Rectangle(100, 100, 64, 64)));

        // add windows to gameobjects
        for (Window window : windows) {
            gameObjects.add(window.gameObject);
        }

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
        long deltaTime = Math.round(1000 / FPS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isValidComposition()) {
                    // update
                    update();
                }

                // redraw
                redraw();

                // the 1 line of code that took me 6 hours to debug:
                // this one actually makes it laggier on windows
                // Toolkit.getDefaultToolkit().sync();
            }
        }, 0, deltaTime);
    }

    public void update() {
        // update
        // update should follow the top down hierarchy
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }

        Player player = (Player) gameObjects.get(0);
        Rectangle windowRect = windows.get(0).panel.rect;
        if (windowRect.isRectangleInside(player.rect)) {
            player.color = Color.RED;
        } else {
            player.color = Color.BLUE;
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

    // if all windows are valid, aka there are currently no overlaps
    public boolean isValidComposition() {
        for (Window window : windows) {
            if (!window.panel.isOverlapping()) {
                return false;
            }
        }
        return true;
    }
}