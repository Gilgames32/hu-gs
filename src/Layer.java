import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Layer extends JPanel {
    Rectangle mainRectangle = null;
    ArrayList<GameObject> children = new ArrayList<GameObject>(0);

    public Layer(Rectangle cRect) {
        setPreferredSize(new Dimension(cRect.sizex, cRect.sizey));
        setDoubleBuffered(true);
        mainRectangle = new Rectangle(0, 0, 0, 0);
        //setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(mainRectangle.x1, mainRectangle.y1, mainRectangle.sizex, mainRectangle.sizey);
        for (GameObject child : children) {
            child.paintComponent(g);
        }
    }

    void onWindowDrag() {
        
    }
}
