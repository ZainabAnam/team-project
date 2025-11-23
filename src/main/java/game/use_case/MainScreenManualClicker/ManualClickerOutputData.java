package game.use_case.MainScreenManualClicker;

public class ManualClickerOutputData {
    private final int newCoinCount;

    public ManualClickerOutputData(int newCoinCount) {
        this.newCoinCount = newCoinCount;
    }

    public int getNewCoinCount() {
        return newCoinCount;
    }

}
