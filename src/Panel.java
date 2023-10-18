import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Panel extends JPanel {
    // some data structure for stuff to draw:
    // probably gotta make one small list for global stuff like player
    // and a local stuff like a tilemap or some
    Rectangle rect;

    public Panel(Rectangle initRect) {
        rect = initRect;

        setPreferredSize(new Dimension(initRect.sizex, initRect.sizey));
        setDoubleBuffered(true);
        setFocusable(true);
        // setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        addKeyListener(World.input);
    }

    public void onWindowDrag() {
        rect.Regenerate(getLocationOnScreen(), getSize());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // loop thru stuff and use their draw
        for (GameObject gameObject : World.gameObjects) {
            gameObject.draw(g, rect);
        }
    }
}
