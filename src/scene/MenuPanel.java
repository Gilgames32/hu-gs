package scene;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.image.ImageObserver;

import java.awt.Graphics;

public class MenuPanel extends JPanel {
    private Image backgroundImage;
    private static final String bgPath = "./assets/nekoarc_zamn.png";

    public MenuPanel() {
        backgroundImage = new ImageIcon(bgPath).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), (ImageObserver) this);
    }
}
