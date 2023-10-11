import javax.swing.*;
import java.awt.*;

public class Layer extends JPanel {
    Rectangle mainRectangle = null;

    public Layer() {
        mainRectangle = new Rectangle(0, 0, 0, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(mainRectangle.x1, mainRectangle.y1, mainRectangle.sizex, mainRectangle.sizey);
    }
}
