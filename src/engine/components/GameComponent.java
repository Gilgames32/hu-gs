package engine.components;

import java.awt.Graphics;

import engine.GameObject;
import util.Coord;

public class GameComponent {
    GameObject gameObject;

    /**
     * Initialize a component by setting it's parent GameObject
     * 
     * @param parent the corresponding parent GameObject
     */
    public void initalize(GameObject parent) {
        gameObject = parent;
    }

    /**
     * Unimplemented draw function for subclasses
     * 
     * @param g      Graphics class for drawing
     * @param offset the offset relative to the window
     */
    public void draw(Graphics g, Coord offset) {
        // abstract
    }

    /**
     * Unimplemented start function for subclasses
     * Called before the first frame
     */
    public void start() {
        // abstract
    }

    /**
     * Unimplemented update function for subclasses
     * Called every frame
     */
    public void update() {
        // abstract
    }
}
