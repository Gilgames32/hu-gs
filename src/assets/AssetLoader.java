package assets;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class AssetLoader {
    /**
     * Retrives the requested asset
     * 
     * @param fileName name of the asset
     * @return the buffered image of the asset
     */
    public static BufferedImage getAsset(String fileName) {
        InputStream stream = AssetLoader.class.getResourceAsStream(fileName);
        try {
            BufferedImage image = ImageIO.read(stream);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
