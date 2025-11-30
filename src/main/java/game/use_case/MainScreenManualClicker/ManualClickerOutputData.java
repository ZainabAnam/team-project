package game.use_case.MainScreenManualClicker;

public class ManualClickerOutputData {
    private final boolean success;
    private final int newCoinCount;

    public ManualClickerOutputData(boolean success, String message, int newCoinCount) {
        this.success = success;
        this.newCoinCount = newCoinCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getNewCoinCount() {
        return newCoinCount;
    }

}
