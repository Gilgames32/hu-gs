import java.awt.*;

public class Player extends GameObject {
    @Override
    protected void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 100, 100);
    }
}
