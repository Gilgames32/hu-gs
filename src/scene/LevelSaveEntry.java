package scene;

import java.io.Serializable;

public class LevelSaveEntry implements Serializable {
    Class<? extends World> levelClass = null;
    boolean isPlayable = false;
    boolean isCompleted = false;

    /**
     * Parameterized constructor
     * 
     * @param lClass the class of the level
     * @param isP if the level is playable
     * @param isC if the level is completed
     */
    LevelSaveEntry(Class<? extends World> lClass, boolean isP, boolean isC) {
        levelClass = lClass;
        isPlayable = isP;
        isCompleted = isC;
    }

    @Override
    public String toString() {
        return levelClass.getName() + "\t" + isPlayable + "\t" + isCompleted;
    }
}
