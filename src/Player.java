import java.awt.*;

public class Player extends GameObject {
    Rectangle rect = null;
    
    public Player() {
        rect = new Rectangle(0, 0, 100, 100);
        
    }

    @Override
    protected void draw(Graphics g, Rectangle windowPosition) {
        g.setColor(Color.RED);
        Rectangle relativeRect = rect.relativeTo(windowPosition);
        g.fillRect(relativeRect.x1, relativeRect.y1, relativeRect.sizex, relativeRect.sizey);
    }

    @Override
    void update() {
        super.update();
        rect.x1 += World.input.getAxisX();
        rect.y1 += World.input.getAxisY();
    }
}
