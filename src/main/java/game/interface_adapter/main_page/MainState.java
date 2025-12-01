package game.interface_adapter.main_page;

import game.entity.User;

public class MainState {
    private User user;
    private String errorMessage;
    private int newCoinCount;

    public MainState(MainState state) {
        this.user = state.user;
        this.errorMessage = state.errorMessage;
        this.newCoinCount = state.newCoinCount;
    }

    public MainState() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getNewCoinCount() {
        return newCoinCount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setNewCoinCount(int newCoinCount) {
        this.newCoinCount = newCoinCount;
    }
}
