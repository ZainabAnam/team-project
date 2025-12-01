package game.interface_adapter.main_page;

import game.use_case.MainScreenManualClicker.ManualClickerInputBoundary;
import game.use_case.MainScreenManualClicker.ManualClickerInputData;

public class MainController {
    private final ManualClickerInputBoundary manualClickerInteractor;

    public MainController(ManualClickerInputBoundary manualClickerInteractor) {
        this.manualClickerInteractor = manualClickerInteractor;
    }

    public void execute() {
        ManualClickerInputData inputData = new ManualClickerInputData();
        manualClickerInteractor.execute(inputData);
    }
}
