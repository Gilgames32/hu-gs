package scene;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import window.*;
import engine.*;
import engine.components.*;

import java.awt.event.MouseEvent;

public class World implements Runnable {
    static public ArrayList<Window> windows = new ArrayList<>();
    static public KeyHandler keyboard = new KeyHandler();
    static public MouseHandler mouse = new MouseHandler();
    static public GameObject root = new GameObject(0, 0, null);

    private static Thread gameThread;
    private static Timer gameTimer;

    // adding a bit of a cooldown after every framedrag so the collision doesnt
    // break :3
    public static int frameCoolDown = 30;

    public String name = "Hello World!";

    /**
     * Default constructor
     */
    public World() {
    }

    /**
     * Resets a world, re-initializes the static variables
     */
    public static void reset() {
        gameTimer.cancel();
        windows = new ArrayList<>();
        keyboard = new KeyHandler();
        mouse = new MouseHandler();
        root = new GameObject(0, 0, null);
    }

    /**
     * Starts the GameObjects, and the gameThread
     */
    public void start() {
        // start stuff here idk
        // for now we use this for initialization

        // initalize gameobjects
        for (GameObject gameObject : root.children) {
            gameObject.start();
        }

        // yay multithreading
        gameThread = new Thread(this);
        // this will call run
        gameThread.start();
    }

    /**
     * The game thread and the main loop, running at 90 FPS
     */
    public void run() {
        int FPS = 90;
        // deltaTime in miliseconds
        long deltaTime = Math.round(1000 / FPS);

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                validateWindows();

                if (frameCoolDown > 0) {
                    frameCoolDown--;
                } else if (isValidComposition()) {
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

    /**
     * Update method, called every frame
     */
    public void update() {
        // update
        // update should follow the top down hierarchy
        for (GameObject gameObject : root.children) {
            gameObject.update();
        }
    }

    /**
     * Calls repaint() on every JFrame to update their content
     */
    public void redraw() {
        // repaint components
        for (Window window : windows) {
            window.frame.repaint();
        }
    }

    /**
     * Notifies the windows that they were clicked
     * 
     * @param e mouse click event
     */
    public static void mouseClicked(MouseEvent e) {
        for (Window window : windows) {
            if (window.frame.isActive()) {
                window.panel.mouseClicked(e);
            }
        }
    }

    /**
     * Decides if the current composition of windows is valid (valid = no overlaps)
     * 
     * @return validity
     */
    public boolean isValidComposition() {
        for (Window window : windows) {
            if (window.panel.isOverlapping(window)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes windows undraggable if there is an invalid entity in them
     */
    public void validateWindows() {
        for (Window window : World.windows) {
            window.draggable = true;
            for (Entity entity : Entity.invalidEntities) {
                if (entity.inWindows.contains(window)) {
                    window.draggable = false;
                }
            }
        }
    }

    /**
     * Makes windows recalulate their sidebounds
     */
    public static void recalcSideBounds() {
        for (Window window : windows) {
            window.panel.recalcBounds();
        }
    }

    public String toString() {
        return name;
    }
}