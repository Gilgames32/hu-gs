import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    int layer = 0;
    Rectangle rect = null;

    protected ArrayList<GameObject> children = new ArrayList<GameObject>();

    void draw(Graphics g, Rectangle windowPosition) {
        if (rect != null) {
            Rectangle relativeRect = rect.relativeTo(windowPosition);
            g.fillRect(relativeRect.x1, relativeRect.y1, relativeRect.sizex, relativeRect.sizey);
        }

    }

    void start() {

    }

    void update() {
        for (GameObject gameObject : children) {
            gameObject.update();
        }
    }
}
