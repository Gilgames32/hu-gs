// import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window {
    JFrame frame = null;
    String title = null;
    Panel panel = null;
    Rectangle rect = null;

    // bool draggable

    public Window(String title, Rectangle initRect) {
        rect = initRect;
        frame = new JFrame(title);
        panel = new Panel(initRect);
        
        // link panel to frame
        frame.add(panel);

        // set up listeners
        frame.addComponentListener(new ComponentAdapter() {
            public void componentMoved(final ComponentEvent e) {
                onWindowDrag();
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set size and location
        frame.setLocation(initRect.x1, initRect.y1);
        // frame.setSize(rect.sizex, rect.sizey); // size is automatically set by making the panel fit
        frame.setResizable(false);
        
        // finalize
        frame.pack();
        frame.setVisible(true);
    }

    void onWindowDrag() {
        panel.onWindowDrag();
    }

}
