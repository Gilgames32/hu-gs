// import java.awt.*;

import javax.swing.*;

public class Window {
    JFrame frame = null;
    String title = null;
    Layer mainLayer = null;

    public Window(String cTitle, Rectangle cRect) {
        frame = new JFrame(cTitle);
        mainLayer = new Layer(cRect);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(cRect.x1, cRect.y1);
        frame.setResizable(false);
        
        frame.add(mainLayer);
        frame.pack();
        
        frame.setVisible(true);
        //frame.setSize(rect.sizex, rect.sizey);
    }

    Rectangle getRectangle() {
        return new Rectangle(mainLayer.getLocationOnScreen(), mainLayer.getSize());
    }
}
