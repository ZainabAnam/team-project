package game.use_case.PetCard.SellPet;

import game.entity.User;

public interface SellPetDataAccessInterface {
    User getCurrentUser();
    void saveUser(User user);
}
