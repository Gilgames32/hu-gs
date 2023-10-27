// import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window {
    JFrame frame = null;
    String title = null;
    Panel panel = null;

    // maybe gameobject?

    // bool draggable

    public Window(String title, Rectangle initRect) {
        frame = new JFrame(title);
        panel = new Panel(initRect);

        // link panel to frame
        frame.add(panel);

        // set up listeners
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(final ComponentEvent e) {
                onWindowDrag();
            }
        });
        frame.addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent e) {
                onWindowDrag();
            }
            public void windowLostFocus(WindowEvent e) {
                // stuff
            }
        });
        
        // set size and location
        frame.setLocation(initRect.x1, initRect.y1);
        // frame.setSize(rect.sizex, rect.sizey); // size is automatically set by making the panel fit
        frame.setResizable(false);
        
        // finalize
        frame.pack();
        frame.setVisible(true);
    }

    void onWindowDrag() {
        try {
            panel.getLocationOnScreen();
        } catch (Exception e) {
            // first frame will always complain
            System.out.println(e);
            return;
        }

        panel.onWindowDrag(this);
    }


}
