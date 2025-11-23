package game.interface_adapter.MainScreenManualClicker;

import game.entity.User;
import game.use_case.MainScreenManualClicker.ManualClickerInputBoundary;
import game.use_case.MainScreenManualClicker.ManualClickerInputData;

public class ManualClickerController {
    private final ManualClickerInputBoundary interactor;

    public ManualClickerController(ManualClickerInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final ManualClickerInputData inputData = new ManualClickerInputData("fillerID");
        this.interactor.execute(inputData);
    }
}
