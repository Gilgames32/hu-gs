package window;

import java.awt.event.*;

public class KeyHandler implements KeyListener {
    boolean w;
    boolean a;
    boolean s;
    boolean d;
    boolean space;

    /**
     * Gets the direction of the horizontal input
     * 
     * @return the x axis of the input
     */
    public int getAxisX() {
        int axisx = 0;
        if (d)
            axisx += 1;
        if (a)
            axisx -= 1;
        return axisx;
    }

    /**
     * Gets the direction of the vertical input
     * 
     * @return the y axis of the input
     */
    public int getAxisY() {
        int axisy = 0;
        // on the screen y is inverted
        if (w)
            axisy += 1;
        if (s)
            axisy -= 1;
        return axisy;
    }

    /**
     * Getter for the state of the space key
     * 
     * @return wether space is pressed
     */
    public boolean getSpace() {
        return space;
    }

    /**
     * Artifiaclly releases every key
     * This is to prevent sticky keys on windows being dragged
     */
    public void releaseAll() {
        // this line looks kinda cool
        w = a = s = d = false;
        space = false;
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

            case KeyEvent.VK_SPACE:
                space = true;
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

            case KeyEvent.VK_SPACE:
                space = false;
                break;

            default:
                break;
        }
    }

}
