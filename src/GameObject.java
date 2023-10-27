import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    int layer = 0;
    Rectangle rect = null;

    protected ArrayList<GameObject> children = new ArrayList<>();

    void draw(Graphics g, Rectangle windowPosition) {
        if (rect != null) {
            Rectangle relativeRect = rect.relativeTo(windowPosition);
            g.fillRect(relativeRect.getX1(), relativeRect.getY1(), relativeRect.sizeX, relativeRect.sizeY);
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
