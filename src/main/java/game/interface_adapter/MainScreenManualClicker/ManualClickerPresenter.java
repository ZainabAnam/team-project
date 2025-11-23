package game.interface_adapter.MainScreenManualClicker;

import game.interface_adapter.ViewManagerModel;
import game.use_case.MainScreenManualClicker.ManualClickerOutputBoundary;
import game.use_case.MainScreenManualClicker.ManualClickerOutputData;

public class ManualClickerPresenter implements ManualClickerOutputBoundary {

    private final ViewManagerModel viewManagerModel;
//  private final ManualClickerViewModel  manualClickerViewModel;

    public ManualClickerPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ManualClickerOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
    }
}
