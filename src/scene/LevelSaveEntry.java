package scene;

import java.io.Serializable;

public class LevelSaveEntry implements Serializable {
    Class<? extends World> levelClass = null;
    boolean isPlayable = false;
    boolean isCompleted = false;

    LevelSaveEntry(Class<? extends World> lClass, boolean isP, boolean isC) {
        levelClass = lClass;
        isPlayable = isP;
        isCompleted = isC;
    }

    @Override
    public String toString() {
        return new String(levelClass.getName() + "\t" + isPlayable + "\t" + isCompleted);
    }
}
