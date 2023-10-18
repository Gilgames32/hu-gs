import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Runnable {
    Thread gameThread;
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    Window w1, w2;
    Layer l1, l2;
    KeyHandler keyHandler;
    Player player;



    public void start() {
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());

        keyHandler = new KeyHandler();

        w1 = new Window("Window1", new Rectangle(500, 500, 800, 600));
        w2 = new Window("Window2", new Rectangle(1300, 500, 800, 600));
        
        l1 = w1.mainLayer;
        l2 = w2.mainLayer;
        l1.addKeyListener(keyHandler);
        l2.addKeyListener(keyHandler);
        player = new Player();
        player.keyHandler = keyHandler;

        gameObjects.add(player);

        gameThread = new Thread(this);
        gameThread.start();
    }

    
    public void run() {
        int FPS = 90;
        // deltaTime in miliseconds
        long deltaTime = Math.round(1000/FPS);
        
        l1.children.add(gameObjects.getFirst());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // update
                update();

                // draw
                l1.repaint();
                l2.repaint();
                Toolkit.getDefaultToolkit().sync();
            }
        }, 0, deltaTime);
    }



    void update() {
        Rectangle ol = w1.rect.Overlap(w2.rect);
        if (ol != null) {
            // System.out.println(ol);
            l1.mainRectangle.Regenerate(ol.Relatively(w1.rect));
            l2.mainRectangle.Regenerate(ol.Relatively(w2.rect));
        }
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }
}
