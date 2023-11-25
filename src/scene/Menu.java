package scene;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import window.Window;

public class Menu extends JFrame {
    private JButton button;
    private JLabel title;
    private JComboBox<World> comboBox;

    List<World> levels = new ArrayList<>();
    int lastLevel = 0;

    public Menu() {
        // load
        loadAndGenerateLevels();

        // window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HU-GS");
        setIconImage(new ImageIcon("./assets/icon.png").getImage());
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());

        // title
        title = new JLabel("HU-GS", SwingConstants.CENTER);
        title.setFont(new Font("VCR OSD Mono", Font.BOLD, 72));
        add(title, BorderLayout.NORTH);

        // panel
        JPanel menuPanel = new JPanel();

        // button
        button = new JButton("LOAD GAME");
        button.addActionListener(new MenuButtonListener());
        menuPanel.add(button);

        // combobox
        comboBox = new JComboBox<>(levels.toArray(new World[0]));
        menuPanel.add(comboBox, BorderLayout.EAST);

        // finalize
        add(menuPanel, BorderLayout.CENTER);
        pack();

        // center
        setLocationRelativeTo(null);
    }

    private class MenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startLevel(comboBox.getSelectedIndex());
        }
    }

    private void loadAndGenerateLevels() {
        Loader.loadLevels();
        for (Class<? extends World> levelClass : Loader.getLevelArray()) {
            try {
                levels.add(levelClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void startLevel(int index) {
        lastLevel = index;
        levels.get(index).start();
        setVisible(false);
    }

    public void onLevelComplete() {
        for (Window window : World.windows) {
            window.frame.setVisible(false);
        }
        World.reset();

        Loader.levelCompletion.replace(levels.get(lastLevel).getClass(), true);
        Loader.saveLevels();

        setVisible(true);
    }
}
