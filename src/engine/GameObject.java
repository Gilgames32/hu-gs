package engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import engine.components.GameComponent;
import engine.components.Transform;
import util.*;

public class GameObject {
    // position and bounds
    public Coord position = new Coord(0, 0);
    public Transform transform = null;

    // hierarchy
    public GameObject parent = null;
    public List<GameObject> children = new ArrayList<>();

    // components
    List<GameComponent> components = new ArrayList<>();

    /**
     * Parameterized constructor
     * 
     * @param x          initial x position
     * @param y          initial y position
     * @param initParent intial parent GameObject
     */
    public GameObject(int x, int y, GameObject initParent) {
        position.x = x;
        position.y = y;
        parent = initParent;
        if (parent != null) {
            parent.children.add(this);
        }
    }

    /**
     * Draw function to draw all components and children
     * 
     * @param g      Graphics
     * @param offset the offset relative to the window
     */
    public void draw(Graphics g, Coord offset) {
        for (GameComponent component : components) {
            component.draw(g, offset.add(position));
        }

        // draw children
        for (GameObject gameObject : children) {
            gameObject.draw(g, offset.add(position));
        }
    }

    /**
     * Start function to start components and children
     * Called before the first frame
     */
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

    /**
     * Update function to update components and children
     * Called every frame
     */
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

    /**
     * Gets the specified type of GameComponent
     * 
     * @param <T>  the type of the component
     * @param type the class of the component
     * @return the component in question, or null if the GameObject does not have
     *         one
     */
    public <T extends GameComponent> T getComponent(Class<T> type) {
        for (GameComponent component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null;
    }

    /**
     * Adds a component to the list of components, and initializes it (sets the
     * parent)
     * Does NOT start the component
     * 
     * @param component the component in question
     */
    public void addComponent(GameComponent component) {
        component.initalize(this);
        components.add(component);
    }

    /**
     * Gets the absolute position (screen space)
     * 
     * @return the absolute position
     */
    public Coord getAbsolutePosition() {
        // nyakkendő
        if (parent != null) {
            return position.add(parent.getAbsolutePosition());
        } else
            return position;
    }

    /**
     * Gets every GameObject which is below this in the hierarchy
     * 
     * @return the list of GameObjects
     */
    public List<GameObject> getAllChildren() {
        // its children
        List<GameObject> allChildren = new LinkedList<>(children);

        // and the children of children, if it has any
        if (!allChildren.isEmpty()) {
            for (GameObject child : children) {
                allChildren.addAll(child.getAllChildren());
            }
        }

        return allChildren;
    }

    /**
     * Destroy this GameObject, and everything below it in the hierarchy
     * Removes it from it's parent's childrens
     */
    public void destroy() {
        destroyAllChildren();
        parent.children.remove(this);
        parent = null;
    }

    /**
     * Destroys all of it's children, and everything below it in the hierarchy
     */
    public void destroyAllChildren() {
        for (GameObject child : children) {
            child.destroyAllChildren();
            child.parent = null;
        }
        children.clear();
    }

}
