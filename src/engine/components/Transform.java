package engine.components;

import util.Rectangle;
import engine.GameObject;
import util.Coord;

public class Transform extends GameComponent {
    Rectangle rect;

    @Override
    public void initalize(GameObject parent) {
        parent.transform = this;
        super.initalize(parent);
    }

    public Transform(int sizeX, int sizeY, int offsetX, int offsetY) {
        rect = new Rectangle(offsetX, offsetY, sizeX, sizeY);
    }

    public Transform(int sizeX, int sizeY) {
        rect = new Rectangle(-sizeX / 2, -sizeY / 2, sizeX, sizeY);
    }

    public Transform(Rectangle r) {
        rect = new Rectangle(r);
    }

    public Rectangle getAbsoluteRectangle() {
        Coord absPos = gameObject.getAbsolutePosition().add(rect.toCoord());
        return new Rectangle(absPos.x, absPos.y, rect.getSizeX(), rect.getSizeY());
    }

}
