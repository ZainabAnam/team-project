package game.interface_adapter.GameStart;

import game.entity.User;

/**
 * The state for the load view Model.
 */
public class GameStartState {
    private boolean loadSuccessful = false;
    private User loadedUser;
    private String errorMessage = null;

    public User getLoadedUser() {
        return loadedUser;
    }
    public boolean isLoadSuccessful() {
        return loadSuccessful;
    }
    public void setLoadedUser(User loadedUser) {
        this.loadedUser = loadedUser;
    }

    public void setLoadSuccessful(boolean loadSuccessful) {
        this.loadSuccessful = loadSuccessful;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
