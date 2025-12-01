package game.use_case.GameSave;

import game.entity.User;

/**
 * The output data from the save game use case.
 */
public class SaveOutputData {

    private final boolean saved;

    public SaveOutputData(boolean saved) {
        this.saved = saved;
    }

    public boolean getSaved() {
        return saved;
    }
}
