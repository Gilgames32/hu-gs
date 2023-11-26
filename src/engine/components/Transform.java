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

    /**
     * Parameterized constructor
     * 
     * @param sizeX   width
     * @param sizeY   height
     * @param offsetX horizontal offset relative to the position of the gameObject
     * @param offsetY vertical offset relative to the position of the gameObject
     */
    public Transform(int sizeX, int sizeY, int offsetX, int offsetY) {
        rect = new Rectangle(offsetX, offsetY, sizeX, sizeY);
    }

    /**
     * Parameterized constructor, offset so it's middle is the gameObjects position
     * 
     * @param sizeX width
     * @param sizeY height
     */
    public Transform(int sizeX, int sizeY) {
        rect = new Rectangle(-sizeX / 2, -sizeY / 2, sizeX, sizeY);
    }

    /**
     * Parameterized constructor
     * 
     * @param r transform rectangle
     */
    public Transform(Rectangle r) {
        rect = new Rectangle(r);
    }

    /**
     * Gets the tranform rectangle in screen space
     * 
     * @return the rectagnle in question
     */
    public Rectangle getAbsoluteRectangle() {
        Coord absPos = gameObject.getAbsolutePosition().add(rect.toCoord());
        return new Rectangle(absPos.x, absPos.y, rect.getSizeX(), rect.getSizeY());
    }

}
