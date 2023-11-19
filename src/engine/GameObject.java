package engine;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.components.GameComponent;
import util.*;

public class GameObject {
    public Coord position = new Coord(0, 0);

    ArrayList<GameComponent> components = new ArrayList<>();

    public ArrayList<GameObject> children = new ArrayList<>();

    public GameObject(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void draw(Graphics g, Coord offset) {
        for (GameComponent component : components) {
            component.draw(g, offset.add(position));
        }

        for (GameObject gameObject : children) {
            gameObject.draw(g, offset.add(position));
        }
    }

    public void start() {
        for (GameComponent component : components) {
            component.start();
        }
        
        for (GameObject gameObject : children) {
            gameObject.start();
        }
    }

    public void update() {
        for (GameComponent component : components) {
            component.update();
        }

        for (GameObject gameObject : children) {
            gameObject.update();
        }
    }


    public <T extends GameComponent> T getComponent(Class<T> type) {
        for (GameComponent component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null;
    }

    public void addComponent(GameComponent component){
        component.initalize(this);
        components.add(component);
    }
}
