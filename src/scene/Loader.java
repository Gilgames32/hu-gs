package scene;

import scene.levels.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static List<LevelSaveEntry> levelCompletion = null;
    public static final String FILENAME = "saves.dat";

    /**
     * Private constructor, so it cannot be instantiated
     */
    private Loader() {
    }

    /**
     * Saves the levels at FILENAME
     */
    public static void saveLevels() {
        File savesFile = new File(FILENAME);
        if (!savesFile.exists()) {
            resetLevels();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savesFile));) {
            oos.writeObject(levelCompletion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the level progression
     */
    public static void resetLevels() {
        levelCompletion = new ArrayList<>();
        // build list
        levelCompletion.add(new LevelSaveEntry(Level1.class, true, false));
        levelCompletion.add(new LevelSaveEntry(Level2.class, false, false));
        levelCompletion.add(new LevelSaveEntry(Level3.class, false, false));
        levelCompletion.add(new LevelSaveEntry(Level4.class, false, false));
        levelCompletion.add(new LevelSaveEntry(Level5.class, false, false));
        levelCompletion.add(new LevelSaveEntry(Level6.class, false, false));
    }

    /**
     * Loads the levels from FILENAME
     */
    @SuppressWarnings("unchecked")
    public static void loadLevels() {
        File savesFile = new File(FILENAME);
        if (!savesFile.exists()) {
            saveLevels();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));) {
            levelCompletion = (List<LevelSaveEntry>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
