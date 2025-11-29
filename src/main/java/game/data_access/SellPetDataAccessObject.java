package game.data_access;
import game.entity.User;
import game.use_case.PetCard.SellPet.SellPetDataAccessInterface;

public class SellPetDataAccessObject implements SellPetDataAccessInterface {
    private User currentUser;

    public SellPetDataAccessObject(User user) {
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

