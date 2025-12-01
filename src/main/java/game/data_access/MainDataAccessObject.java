package game.data_access;

import game.entity.User;
import game.use_case.MainScreenManualClicker.ManualClickerUserDataAccessInterface;

public class MainDataAccessObject implements ManualClickerUserDataAccessInterface {
    private User user;

    public MainDataAccessObject() {
        this.user = new User();
    }

    @Override
    public User getUser() {
        return user;
    }
}
