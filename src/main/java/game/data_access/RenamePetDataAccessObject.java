package game.data_access;

import game.entity.User;
import game.use_case.PetCard.RenamePet.RenamePetDataAccessInterface;

public class RenamePetDataAccessObject implements RenamePetDataAccessInterface {
    private User currentUser;

    public RenamePetDataAccessObject(User user) {
        this.currentUser = user;
    }

    @Override
    public User getUser(String username) {
        return currentUser;
    }

    @Override
    public void saveUser(User user) {
        this.currentUser = user;

    }
}