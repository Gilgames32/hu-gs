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
    }
}
