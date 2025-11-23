package game.use_case.MainScreenManualClicker;

import game.entity.User;

public class ManualClickerInputData {
    private final String userID;

    public ManualClickerInputData(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return this.userID;
    }

}
