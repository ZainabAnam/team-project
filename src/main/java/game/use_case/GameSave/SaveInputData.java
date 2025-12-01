package game.use_case.GameSave;

import game.entity.User;

/**
 * Input data for the Game Save use case
 */
public class SaveInputData {
    private final User user;

    public SaveInputData(User user) {
        this.user = user;
    }

    public User getUser() { return user; }
}
