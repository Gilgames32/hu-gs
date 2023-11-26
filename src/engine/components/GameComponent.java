package engine.components;

import java.awt.Graphics;

import engine.GameObject;
import util.Coord;

import java.awt.image.ImageObserver;

public class GameComponent {
    GameObject gameObject;

    public void initalize(GameObject parent) {
        gameObject = parent;
    }

    public void draw(Graphics g, Coord offset, ImageObserver imgObs) {
        // abstract
    }

    public void start() {
        // abstract
    }

    public void update() {
        // abstract
    }
}
