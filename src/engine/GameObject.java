package engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import engine.components.GameComponent;
import util.*;

public class GameObject {
    public Coord position = new Coord(0, 0);
    
    List<GameComponent> components = new ArrayList<>();
    
    public GameObject parent = null;
    public List<GameObject> children = new ArrayList<>();

    public GameObject(int x, int y, GameObject initParent) {
        position.x = x;
        position.y = y;
        parent = initParent;
        if (parent != null) {
            parent.children.add(this);
        }
    }

    public void draw(Graphics g, Coord offset) {
        for (GameComponent component : components) {
            component.draw(g, offset.add(position));
        }

        // draw children
        for (GameObject gameObject : children) {
            gameObject.draw(g, offset.add(position));
        }
    }

    public void start() {
        // start itself
        for (GameComponent component : components) {
            component.start();
        }
        
        // start children
        for (GameObject gameObject : children) {
            gameObject.start();
        }
    }

    public void update() {
        // update self
        for (GameComponent component : components) {
            component.update();
        }

        // update children
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

    public Coord getAbsolutePosition(){
        // nyakkendő
        if (parent != null) {
            return position.add(parent.getAbsolutePosition());
        }
        else return position;
    }

    public List<GameObject> getAllChildren() {
        // its children
        List <GameObject> allChildren = new LinkedList<>(children);

        // and the children of children, if it has any
        if (!allChildren.isEmpty()) {
            for (GameObject child : children) {
                allChildren.addAll(child.getAllChildren());
            }
        }

        return allChildren;
    }

}
