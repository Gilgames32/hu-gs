package window;
import java.awt.event.*;

import game.World;

public class MouseHandler implements MouseListener {
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        World.mouseClicked(e);
    }
}
