package engine.components;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import engine.GameObject;
import util.Coord;

public class Sprite extends GameComponent {

    BufferedImage original;
    BufferedImage image;
    BufferedImage flippedImage;
    public boolean flip;

    public Sprite(String fileName) {
        try {
            original = ImageIO.read(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sprite(BufferedImage bImage) {
        original = bImage;
    }

    @Override
    public void initalize(GameObject parent) {
        super.initalize(parent);

        // scale
        double scaleX = (double) gameObject.transform.rect.getSizeX() / original.getWidth();
        double scaleY = (double) gameObject.transform.rect.getSizeY() / original.getHeight();
        if (scaleX == 1 && scaleY == 1) {
            image = original;
        } else {
            AffineTransform scaleInstance = AffineTransform.getScaleInstance(scaleX, scaleY);
            AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = scaleOp.filter(original, null);
        }

        // flip
        AffineTransform flipInstance = AffineTransform.getScaleInstance(-1, 1);
        flipInstance.translate(-image.getWidth(null), 0);
        AffineTransformOp flipOp = new AffineTransformOp(flipInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        flippedImage = flipOp.filter(image, null);
    }

    @Override
    public void draw(Graphics g, Coord offset, ImageObserver imgObs) {
        Coord imageOffset = offset.add(gameObject.transform.rect.toCoord());
        g.drawImage(flip ? flippedImage : image, imageOffset.x, imageOffset.y, imgObs);
    }
}
