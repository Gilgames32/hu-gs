package game;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import window.*;
import engine.*;
import engine.components.*;
import util.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class World implements Runnable {
    static public ArrayList<Window> windows = new ArrayList<>();
    static public KeyHandler keyboard = new KeyHandler();
    static public MouseHandler mouse = new MouseHandler();

    static public GameObject root = new GameObject(0, 0, null);

    Thread gameThread;

    public void start() {
        // start stuff here idk
        // for now we use this for initialization
        windows.add(new Window("UwU", new Rectangle(100, 100, 400, 300)));
        windows.add(new Window("OwO", new Rectangle(100 + 400, 100, 400, 300)));
        windows.add(new Window(">w<", new Rectangle(100, 100 + 300, 200, 150)));
        
        GameObject player = new GameObject(300, 200, root);
        player.addComponent(new Transform(50, 50));
        player.addComponent(new BoxCollider());
        player.addComponent(new Box(Color.MAGENTA));
        player.addComponent(new Player());
        player.addComponent(new Rigidbody());
        player.addComponent(new Entity());
        
        GameObject box = new GameObject(100, 100, windows.get(0).panel.gameObject);
        box.addComponent(new Transform(400, 50, 0, 0));
        box.addComponent(new BoxCollider());
        box.addComponent(new Box());







        // initalize gameobjects
        for (GameObject gameObject : root.children) {
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
                validateWindows();

                if (Window.frameCoolDown > 0) {
                    Window.frameCoolDown--;
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

    public void update() {
        // update
        // update should follow the top down hierarchy
        for (GameObject gameObject : root.children) {
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

    // if all windows are valid, aka there are currently no overlaps
    public boolean isValidComposition() {
        for (Window window : windows) {
            if (!window.panel.isOverlapping()) {
                return false;
            }
        }
        return true;
    }

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

    public static void recalcSideBounds() {
        for (Window window : windows) {
            window.panel.recalcBounds();
        }
    }
}