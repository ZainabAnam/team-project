package game.use_case.MainScreenManualClicker;

public interface ManualClickerOutputBoundary {
    void prepareSuccessView(ManualClickerOutputData outputData);
    void prepareFailView(String error);
}
