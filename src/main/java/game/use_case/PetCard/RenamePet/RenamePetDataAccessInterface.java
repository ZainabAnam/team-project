package game.use_case.PetCard.RenamePet;

import game.entity.User;

public interface RenamePetDataAccessInterface {
    User getUser(String username);
    void saveUser(User user);
}