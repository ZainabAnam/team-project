package game.use_case.PetCard.IncreaseAffection;
import game.entity.User;
import game.entity.Pet;

public interface IncreaseAffectionUserDataAccessInterface {
    User getUser(String userID);
    Boolean userExists(String userID);
}
