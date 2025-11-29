package game.use_case.PetCard.IncreaseEnergy;

import game.entity.User;

public interface IncreaseEnergyUserDataAccessInterface {
    User getUser(String userID);
    Boolean userExists(String userID);
}
