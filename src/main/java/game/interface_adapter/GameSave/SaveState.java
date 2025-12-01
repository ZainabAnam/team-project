package game.interface_adapter.GameSave;

/**
 * States for the save game use case
 */
public class SaveState {
    private boolean saveSuccessful = false;
    private String errorMessage = null;

    public boolean isSaveSuccessful() {
        return saveSuccessful;
    }
    public void setSaveSuccessful(boolean saveSuccessful) {
        this.saveSuccessful = saveSuccessful;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
