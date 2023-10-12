// import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window {
    JFrame frame = null;
    String title = null;
    Layer mainLayer = null;
    Rectangle rect = null;

    public Window(String cTitle, Rectangle cRect) {
        rect = cRect;
        frame = new JFrame(cTitle);
        mainLayer = new Layer(cRect);

        // set up listeners
        frame.addComponentListener(new ComponentAdapter() {
            public void componentMoved(final ComponentEvent e) {
                onWindowDrag();
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(cRect.x1, cRect.y1);
        frame.setResizable(false);
        
        frame.add(mainLayer);
        frame.pack();
        
        frame.setVisible(true);
        //frame.setSize(rect.sizex, rect.sizey);
    }

    void onWindowDrag() {
        try {
            rect.Regenerate(mainLayer.getLocationOnScreen(), mainLayer.getSize());
        } catch (Exception e) {
            // will always throw error the first frame
            System.out.println(e);
        }
        
        mainLayer.onWindowDrag();
    }

}
