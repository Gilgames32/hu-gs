package scene;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import window.Window;

public class Menu extends JFrame {
    public ImageIcon icon;
    private JComboBox<World> comboBox;
    private DefaultComboBoxModel<World> comboBoxModel;

    List<World> levels = new ArrayList<>();
    int lastLevel = 0;

    /**
     * Default constructor
     * Builds the UI and loads the saves
     */
    public Menu() {
        // window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HU-GS Universe - Ghost Screens");
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        setResizable(false);

        // icon
        icon = new ImageIcon("./assets/icon.png");
        setIconImage(icon.getImage());

        // title
        JLabel title = new JLabel("HU-GS", SwingConstants.CENTER);
        title.setFont(new Font("VCR OSD Mono", Font.BOLD, 72));
        add(title, BorderLayout.NORTH);

        // panel
        JPanel menuPanel = new JPanel();

        // button
        JButton button = new JButton("LOAD LEVEL");
        button.addActionListener(new MenuButtonListener());
        menuPanel.add(button);

        // combobox
        comboBoxModel = new DefaultComboBoxModel<>(levels.toArray(new World[0]));
        comboBox = new JComboBox<>(comboBoxModel);
        menuPanel.add(comboBox, BorderLayout.EAST);

        // image
        ImageIcon upscaledImg = new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() * 4,
                icon.getIconHeight() * 4, Image.SCALE_SMOOTH));
        JLabel imgLabel = new JLabel(upscaledImg);
        // extra margin
        imgLabel.setPreferredSize(new Dimension(icon.getIconWidth() * 5, icon.getIconHeight() * 5));
        add(imgLabel, BorderLayout.SOUTH);

        // finalize
        add(menuPanel, BorderLayout.CENTER);
        pack();

        // center
        setLocationRelativeTo(null);

        // load
        loadAndGenerateLevels();
    }

    /**
     * Listener for the load level button
     */
    private class MenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startLevel(comboBox.getSelectedIndex());
        }
    }

    /**
     * Loads the playable level classes and instantiates the level objects
     */
    private void loadAndGenerateLevels() {
        Loader.loadLevels();

        levels = new ArrayList<>();

        for (LevelSaveEntry levelEntry : Loader.levelCompletion) {
            try {
                if (levelEntry.isPlayable) {
                    levels.add(levelEntry.levelClass.getDeclaredConstructor().newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        comboBoxModel.removeAllElements();
        comboBoxModel.addAll(levels);
        comboBox.setSelectedIndex(levels.size() - 1);

    }

    /**
     * Starts the index-th level
     * 
     * @param index
     */
    public void startLevel(int index) {
        lastLevel = index;
        if (index < 0) {
            return;
        }
        levels.get(index).start();
        setVisible(false);
    }

    /**
     * Called when a level is completed
     * Marks the level completed and the next one playable
     * Saves the progress
     */
    public void onLevelComplete() {
        for (Window window : World.windows) {
            window.frame.setVisible(false);
        }
        World.reset();

        Loader.levelCompletion.get(lastLevel).isCompleted = true;
        if (lastLevel + 1 < Loader.levelCompletion.size()) {
            Loader.levelCompletion.get(lastLevel + 1).isPlayable = true;
        }
        Loader.saveLevels();

        loadAndGenerateLevels();
        setVisible(true);
    }
}
