package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import engine.GameObject;
import engine.components.Transform;
import util.Coord;
import util.Rectangle;

public class Tests {
    // Rectangle tests
    @Test
    public void testOverlap() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, 3, 5, 5);

        Rectangle overlap = rect1.overlap(rect2);

        assertNotNull(overlap);
        assertEquals(3, overlap.getX1());
        assertEquals(3, overlap.getY1());
        assertEquals(2, overlap.getSizeX());
        assertEquals(2, overlap.getSizeY());
    }

    @Test
    public void testOverlapNoOverlap() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(6, 6, 5, 5);

        assertNull(rect1.overlap(rect2));
    }

    @Test
    public void testIsPointInside() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);

        assertTrue(rect.isPointInside(5, 5));
        assertFalse(rect.isPointInside(15, 15));
    }

    @Test
    public void testIsRectangleInside() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(2, 2, 5, 5);
        Rectangle rect3 = new Rectangle(12, 12, 5, 5);

        assertTrue(rect1.isRectangleInside(rect2));
        assertFalse(rect1.isRectangleInside(rect3));
    }

    @Test
    public void testAddPos() {
        Rectangle rect1 = new Rectangle(5, 5, 10, 10);
        Rectangle rect2 = new Rectangle(2, 2, 3, 3);

        Rectangle result = rect1.addPos(rect2);

        assertEquals(7, result.getX1());
        assertEquals(7, result.getY1());
        assertEquals(10, result.getSizeX());
        assertEquals(10, result.getSizeY());
    }

    @Test
    public void testSubPos() {
        Rectangle rect1 = new Rectangle(5, 5, 10, 10);
        Rectangle rect2 = new Rectangle(2, 2, 3, 3);

        Rectangle result = rect1.subPos(rect2);

        assertEquals(3, result.getX1());
        assertEquals(3, result.getY1());
        assertEquals(10, result.getSizeX());
        assertEquals(10, result.getSizeY());
    }


    // Coord tests
    @Test
    public void testAdd() {
        Coord coord1 = new Coord(2, 3);
        Coord coord2 = new Coord(5, 7);

        Coord result = coord1.add(coord2);

        assertEquals(7, result.x);
        assertEquals(10, result.y);
    }

    @Test
    public void testSub() {
        Coord coord1 = new Coord(8, 5);
        Coord coord2 = new Coord(3, 2);

        Coord result = coord1.sub(coord2);

        assertEquals(5, result.x);
        assertEquals(3, result.y);
    }

    @Test
    public void testMultiply() {
        Coord coord = new Coord(3, 4);

        Coord result = coord.multiply(2.5);

        assertEquals(7, result.x);
        assertEquals(10, result.y);
    }


    // GameObject and Transform test
    @Test
    public void testAddComponent() {
        GameObject gameObject = new GameObject(1, 2, null);
        gameObject.addComponent(new Transform(10, 20, -1, -2));
        Transform transform = gameObject.getComponent(Transform.class);
        
        assertNotNull(transform);
        
        Rectangle absRect = transform.getAbsoluteRectangle();
        assertEquals(0, absRect.getX1());
        assertEquals(0, absRect.getY1());
        assertEquals(10, absRect.getSizeX());
        assertEquals(20, absRect.getSizeY());
    }

}