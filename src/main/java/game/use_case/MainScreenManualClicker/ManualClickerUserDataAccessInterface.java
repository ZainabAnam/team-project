package game.use_case.MainScreenManualClicker;
import game.entity.User;

public interface ManualClickerUserDataAccessInterface {
    User getUser(String userID);
    Boolean userExists(String userID);
}
