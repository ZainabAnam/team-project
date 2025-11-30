package game.interface_adapter.MainScreenManualClicker;

import game.entity.User;
import game.use_case.MainScreenManualClicker.ManualClickerInputBoundary;
import game.use_case.MainScreenManualClicker.ManualClickerInputData;

public class ManualClickerController {
    private final ManualClickerInputBoundary interactor;

    public ManualClickerController(ManualClickerInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String userID) {
        final ManualClickerInputData inputData = new ManualClickerInputData(userID);
        this.interactor.execute(inputData);
    }
}
