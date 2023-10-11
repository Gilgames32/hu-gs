import java.awt.Toolkit;

public class Game extends Thread{
    public void run() {
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
        Window w1 = new Window("Window1", new Rectangle(500, 500, 800, 600));
        Window w2 = new Window("Window2", new Rectangle(1300, 500, 800, 600));

        while (true) {
            Rectangle ol = w1.getRectangle().Overlap(w2.getRectangle());
            if (ol != null) {
                System.out.println(ol);
                w1.mainLayer.mainRectangle.Regenerate(ol.Relatively(w1.rect));
                w1.frame.repaint();
                w2.mainLayer.mainRectangle.Regenerate(ol.Relatively(w2.rect));
                w2.frame.repaint();
            }

            // throttle
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }
}
