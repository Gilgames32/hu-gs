package engine.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import assets.AssetLoader;
import util.Coord;
import util.Rectangle;

public class Sprite extends GameComponent {

    BufferedImage image;
    public boolean flip;

    /**
     * Parameterized constructor
     * 
     * @param fileName path to the image file
     */
    public Sprite(String fileName) {
        image = AssetLoader.getAsset(fileName);
    }

    /**
     * Parameterized constructor
     * 
     * @param bImage buffered image of the sprite
     */
    public Sprite(BufferedImage bImage) {
        image = bImage;
    }

    @Override
    public void draw(Graphics g, Coord offset) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle bounds = gameObject.transform.rect;
        Coord imageOffset = offset.add(bounds.toCoord());
        if (!flip) {
            g2d.drawImage(image, imageOffset.x, imageOffset.y, bounds.getSizeX(), bounds.getSizeY(), null);
        } else {
            g2d.drawImage(image, imageOffset.x + bounds.getSizeX(), imageOffset.y, -bounds.getSizeX(),
                    bounds.getSizeY(), null);
        }
    }
}
