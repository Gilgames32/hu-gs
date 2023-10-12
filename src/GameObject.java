import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    int layer = 0;

    protected ArrayList<GameObject> children = new ArrayList<GameObject>();

    void draw(Graphics g) {}

    void update() {
        for (GameObject gameObject : children) {
            gameObject.update();
        }
    }
}
