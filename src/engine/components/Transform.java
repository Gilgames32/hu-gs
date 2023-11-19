package engine.components;

import util.Rectangle;

public class Transform extends GameComponent {
    Rectangle rect;

    public Transform(int sizeX, int sizeY, int offsetX, int offsetY) {
        rect = new Rectangle(offsetX, offsetY, sizeX, sizeY);
    }

    public Transform(int sizeX, int sizeY) {
        rect = new Rectangle(-sizeX/2, -sizeY/2, sizeX, sizeY);
    }

    public Rectangle getAbsoluteRectangle() {
        return new Rectangle(gameObject.position.x + rect.getX1(), gameObject.position.y + rect.getY1(), rect.getSizeX(), rect.getSizeY());
    }

}
