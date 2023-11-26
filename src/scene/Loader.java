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
    public static final String fileName = "saves.dat";

    public static void saveLevels() {
        System.out.println("Save");
        File savesFile = new File(fileName);
        if (!savesFile.exists()) {
            resetLevels();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savesFile));
            oos.writeObject(levelCompletion);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        print();
    }

    public static void resetLevels() {
        levelCompletion = new ArrayList<>();
        // build list
        // levelCompletion.add(new LevelSaveEntry(DebugLevel.class, true, false));
        levelCompletion.add(new LevelSaveEntry(Level1.class, true, false));
        levelCompletion.add(new LevelSaveEntry(Level2.class, true, false));
        levelCompletion.add(new LevelSaveEntry(Level3.class, true, false));

        System.out.println("Levels reset");
    }

    @SuppressWarnings("unchecked")
    public static void loadLevels() {
        System.out.println("Load");
        File savesFile = new File(fileName);
        if (!savesFile.exists()) {
            saveLevels();
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            levelCompletion = (List<LevelSaveEntry>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        print();
    }

    private static void print() {
        for (LevelSaveEntry levelSaveEntry : levelCompletion) {
            System.out.println(levelSaveEntry);
        }
    }
}
