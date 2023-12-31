package window;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import scene.App;
import scene.World;
import util.Rectangle;

public class Window {
    public JFrame frame = null;
    String title = null;
    public Panel panel = null;
    Point windowPos = null;
    public boolean draggable = true;

    /**
     * Parameterized constructor
     * 
     * @param title
     * @param initRect
     */
    public Window(String title, Rectangle initRect) {
        // move frame relative to the center of the screen
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        initRect = initRect.addPos((int) screensize.getWidth() / 2, (int) screensize.getHeight() / 2)
                .subPos(initRect.getSizeX() / 2, initRect.getSizeY() / 2);

        frame = new JFrame(title);
        panel = new Panel(initRect);

        // link panel to frame
        frame.add(panel);

        // set up listeners
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        // size is automatically set by making the panel fit
        frame.setResizable(false);

        // set icon
        frame.setIconImage(App.menu.icon.getImage());

        // finalize
        frame.pack();
        frame.setVisible(true);

        World.windows.add(this);
    }

    /**
     * Listener function
     * Called when a window is moved or it's focus is changed
     * Cancels moves if the window is not draggable
     */
    void onWindowDrag() {
        // this is to not get errors on the first few frames due to some desktop
        // environments
        if (!frame.isShowing()) {
            return;
        }

        if (draggable) {
            windowPos = frame.getLocationOnScreen();
            panel.onWindowDrag(this);
        } else {
            frame.setLocation(windowPos);
        }

        World.frameCoolDown = 4;
    }

}
