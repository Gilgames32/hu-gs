import java.awt.*;

public class Player extends GameObject {
    double speed = 10;
    
    public Player() {
        rect = new Rectangle(1000, 800, 100, 100);
        
    }

    @Override
    protected void draw(Graphics g, Rectangle windowPosition) {
        g.setColor(Color.RED);
        super.draw(g, windowPosition);
    }

    @Override
    void update() {
        super.update();
        rect.x1 += World.keyboard.getAxisX() * speed;
        rect.y1 += World.keyboard.getAxisY() * speed;
    }
}
