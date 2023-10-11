// import java.awt.*;

import javax.swing.*;

public class HWindow {
    JFrame frame = null;
    String title = null;
    Rectangle rect = null;
    Layer mainLayer = null;

    public HWindow(String cTitle, Rectangle cRect) {
        rect = cRect;
        frame = new JFrame(cTitle);
        mainLayer = new Layer();
        frame.add(mainLayer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(rect.x1, rect.y1);
        frame.pack();
        frame.setVisible(true);
        System.out.println(frame.getLocation());
        System.out.println(frame.getLocationOnScreen());
        System.out.println(frame.getSize());
        frame.setSize(rect.sizex, rect.sizey);
    }

    Rectangle getRectangle() {
        rect.Regenerate(frame.getLocation(), frame.getSize());
        return rect;
    }
}
