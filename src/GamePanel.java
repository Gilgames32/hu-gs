import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int screenWidth = 1280;
    final int screenHeight = 720;

    long deltaTime = 0;

    Thread gameThread;

    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        // delta variables
        long lastTime = System.nanoTime();
        long currentTime;
        double drawInterwal = 1e9 / 90;
        double delta = 0;        
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            // this way delta is a 0-1 scale indicating how far we are till the next update
            delta += (currentTime - lastTime) / drawInterwal;
            deltaTime += currentTime - lastTime;
            lastTime = currentTime;

            // whenthe scale is over 1 we can 
            if (delta > 1) {
                delta--;
                System.out.println(1.0/deltaTime*1e9);
                deltaTime = 0;
                
                // update

                // repaint
                repaint();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(10, 10, 10, 10);
        g.drawString(""+(1.0/deltaTime*1e9), 10, 100);
    }
}
