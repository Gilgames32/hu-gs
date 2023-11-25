package engine.components;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import util.Coord;

public class Sprite extends GameComponent {

    BufferedImage image, flippedImage;
    public boolean flip;

    public Sprite(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            flippedImage = op.filter(image, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    @Override
    public void draw(Graphics g, Coord offset, ImageObserver imgObs) {
        Coord imageOffset = offset.add(gameObject.transform.rect.toCoord());
        g.drawImage(flip ? flippedImage : image, imageOffset.x, imageOffset.y, imgObs);
    }
}
