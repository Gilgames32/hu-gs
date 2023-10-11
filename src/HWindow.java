// import java.awt.*;

import javax.swing.*;

public class HWindow {
    JFrame frame = null;
    String title = null;
    Rectangle rect = null;
    Layer mainLayer = null;

    public HWindow(String cTitle, Rectangle cRect) {
        rect = cRect;
        frame = new JFrame(cTitle);
        mainLayer = new Layer(cRect);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(rect.x1, rect.y1);
        frame.setResizable(false);
        
        frame.add(mainLayer);
        frame.pack();
        
        frame.setVisible(true);
        //frame.setSize(rect.sizex, rect.sizey);
    }

    Rectangle getRectangle() {
        rect.Regenerate(mainLayer.getLocationOnScreen(), mainLayer.getSize());
        return rect;
    }
}
