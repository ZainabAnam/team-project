package game.use_case.Collections;

import game.entity.User;

public interface CollectionsDataAccessInterface {
    User getCurrentUser();
    void saveUsr(User user);
}
