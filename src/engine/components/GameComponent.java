package engine.components;

import java.awt.Graphics;

import engine.GameObject;
import util.Coord;

public class GameComponent {
    GameObject gameObject;
    
    public void initalize(GameObject parent) {
        gameObject = parent;
    }

    public void draw(Graphics g, Coord offset) {

    }
    public void start() {

    }
    public void update() {

    }
}
