import java.awt.Toolkit;
import java.util.ArrayList;

public class Game implements Runnable{
    Thread gameThread;
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    Window w1, w2;
    Layer l1, l2;



    public void start() {
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());

        w1 = new Window("Window1", new Rectangle(500, 500, 800, 600));
        w2 = new Window("Window2", new Rectangle(1300, 500, 800, 600));
        l1 = w1.mainLayer;
        l2 = w2.mainLayer;
        gameObjects.add(new Player());

        gameThread = new Thread(this);
        gameThread.start();
    }

    
    public void run() {
        // delta variables
        long lastTime = System.nanoTime();
        long currentTime;
        double drawInterwal = 1e9 / 90;
        double delta = 0;
        
        l1.children.add(gameObjects.getFirst());
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            // this way delta is a 0-1 scale indicating how far we are till the next update
            delta += (currentTime - lastTime) / drawInterwal;
            lastTime = currentTime;
            // whenthe scale is over 1 we can 
            if (delta > 1) {
                delta--;

                // update
                update();

                // draw
                l1.repaint();
                l2.repaint();
            }
        }
    }

    void update() {
        Rectangle ol = w1.rect.Overlap(w2.rect);
        if (ol != null) {
            // System.out.println(ol);
            l1.mainRectangle.Regenerate(ol.Relatively(w1.rect));
            l2.mainRectangle.Regenerate(ol.Relatively(w2.rect));
        }
    }
}
