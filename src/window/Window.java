package window;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import engine.GameObject;
import util.Rectangle;

public class Window {
    public JFrame frame = null;
    String title = null;
    public Panel panel = null;
    Point windowPos = null;
    public boolean draggable = true;

    public GameObject gameObject = null;

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
                onWindowDrag();
            }
        });

        // set size and location
        windowPos = new Point(initRect.getX1(), initRect.getY1());
        frame.setLocation(windowPos);
        // frame.setSize(rect.sizex, rect.sizey);
        // size is automatically set by making the panel fit
        frame.setResizable(false);

        // finalize
        frame.pack();
        frame.setVisible(true);

        // initialize gameobject
        gameObject = new GameObject(0, 0);
    }

    void onWindowDrag() {
        // this is to not get errors on the first few frames due to some desktop
        // environments
        if (frame.isShowing()) {
            if (draggable) {
                windowPos = frame.getLocationOnScreen();
                panel.onWindowDrag(this);
            } else {
                frame.setLocation(windowPos);
            }

        }

    }

}