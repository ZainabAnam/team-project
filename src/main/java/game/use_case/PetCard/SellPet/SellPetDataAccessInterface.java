package game.use_case.PetCard.SellPet;

import game.entity.User;

public interface SellPetDataAccessInterface {
    User getUser(String username);
    void saveUser(User user);
}
