package game.data_access;

import game.entity.User;

public interface UserDataAccessObjectInterface {
    /** Load a new file */
    User newUser();

    /** Load user data from persistence or memory */
    User loadUser();

    /** Save user data to persistence or memory */
    void saveUser(User user);
}
