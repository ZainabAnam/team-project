package game.use_case.GameStart;

import game.entity.User;

/**
 * The output data from the start game use case.
 */
public class GameStartOutputData {

    private final User user;

    public GameStartOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
