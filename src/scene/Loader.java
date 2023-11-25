package scene;

import java.util.Map;

import scene.levels.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Loader {
    public static Map<Class<? extends World>, Boolean> levelCompletion = null;
    public static final String fileName = "saves.dat";

    public static void saveLevels() {
        File savesFile = new File(fileName);
        if (!savesFile.exists()) {
            System.out.println("gex");
            resetLevels();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savesFile));
            oos.writeObject(levelCompletion);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetLevels() {
        levelCompletion = new HashMap<>();
        List<Class<? extends World>> levels = new ArrayList<>();
        // build levellist
        levels.add(DebugLevel.class);
        levels.add(Level1.class);

        for (Class<? extends World> level : levels) {
            levelCompletion.put(level, false);
        }
        levelCompletion.replace(levels.get(0), true);
    }

    @SuppressWarnings("unchecked")
    public static void loadLevels() {
        File savesFile = new File(fileName);
        if (!savesFile.exists()) {
            System.out.println("say");
            resetLevels();
            return;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            levelCompletion = (Map<Class<? extends World>, Boolean>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Class<? extends World>> getLevelArray() {
        if (levelCompletion == null) {
            loadLevels();
        }

        return new ArrayList<>(levelCompletion.keySet());
    }
}
