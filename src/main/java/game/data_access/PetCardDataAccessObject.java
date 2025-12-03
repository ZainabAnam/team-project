package game.data_access;

import game.entity.User;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionUserDataAccessInterface;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyUserDataAccessInterface;

public class PetCardDataAccessObject implements IncreaseEnergyUserDataAccessInterface, IncreaseAffectionUserDataAccessInterface {
    private User user;

    public PetCardDataAccessObject() {
        this.user = new User();
    }

    @Override
    public User getUser() {
        return user;
    }
}
