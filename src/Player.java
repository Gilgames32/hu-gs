import java.awt.*;

public class Player extends GameObject {
    double speed = 10;
    Color color = Color.RED;

    public Player(Rectangle prect) {
        rect = prect;
    }

    @Override
    protected void draw(Graphics g, Rectangle windowPosition) {
        g.setColor(color);
        super.draw(g, windowPosition);
    }

    @Override
    void update() {
        super.update();
        rect.x += World.keyboard.getAxisX() * speed;
        rect.y += World.keyboard.getAxisY() * speed;
        inWindows();
    }

    int inWindows() {
        int windownCount = 0;
        for (Window window : World.windows) {
            if (rect.overlap(window.panel.rect) != null) {
                windownCount++;
            }
        }

        for (Window window : World.windows) {
            // this will set draggable true if its in less than two windows or the player is
            // not in that window
            window.draggable = rect.overlap(window.panel.rect) == null || windownCount < 2;
        }

        return windownCount;
    }
}
