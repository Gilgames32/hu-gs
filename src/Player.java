import java.awt.*;

public class Player extends GameObject {
    KeyHandler keyHandler = null;
    Rectangle rect = null;
    
    public Player() {
        rect = new Rectangle(0, 0, 100, 100);
    }

    @Override
    protected void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(rect.x1, rect.y1, rect.sizex, rect.sizey);
    }

    @Override
    void update() {
        super.update();
        rect.x1 += keyHandler.getAxisX();
        rect.y1 += keyHandler.getAxisY();
    }
}
