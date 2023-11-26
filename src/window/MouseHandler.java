package window;

import java.awt.event.*;

import scene.World;

public class MouseHandler implements MouseListener {
    public void mousePressed(MouseEvent e) {
        // pass
    }

    public void mouseReleased(MouseEvent e) {
        // pass
    }

    public void mouseEntered(MouseEvent e) {
        // pass
    }

    public void mouseExited(MouseEvent e) {
        // pass
    }

    public void mouseClicked(MouseEvent e) {
        World.mouseClicked(e);
    }
}
