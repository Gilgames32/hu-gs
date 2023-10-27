import java.awt.event.*;

public class KeyHandler implements KeyListener {
    boolean w, a, s, d;

    public int getAxisX() {
        int axisx = 0;
        if (d)
            axisx += 1;
        if (a)
            axisx -= 1;
        return axisx;
    }

    public int getAxisY() {
        int axisy = 0;
        // on the screen y is inverted
        if (w)
            axisy -= 1;
        if (s)
            axisy += 1;
        return axisy;
    }

    public void releaseAll() {
        // this line looks kinda cool
        w = a = s = d = false;
    }

    public void keyTyped(KeyEvent event) {
        // we must implement this
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                w = true;
                break;

            case KeyEvent.VK_A:
                a = true;
                break;

            case KeyEvent.VK_S:
                s = true;
                break;

            case KeyEvent.VK_D:
                d = true;
                break;

            default:
                break;
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                w = false;
                break;

            case KeyEvent.VK_A:
                a = false;
                break;

            case KeyEvent.VK_S:
                s = false;
                break;

            case KeyEvent.VK_D:
                d = false;
                break;

            default:
                break;
        }
    }

}
