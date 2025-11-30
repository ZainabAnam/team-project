package game.interface_adapter.main_page;

import game.interface_adapter.MainScreenManualClicker.ManualClickerState;
import game.interface_adapter.main_page.MainViewModel;
import game.interface_adapter.ViewManagerModel;
import game.use_case.MainScreenManualClicker.ManualClickerOutputBoundary;
import game.use_case.MainScreenManualClicker.ManualClickerOutputData;
import game.use_case.MainScreenManualClicker.ManualClickerUserDataAccessInterface;

public class MainPresenter implements ManualClickerOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final MainViewModel mainViewModel;
    private final ManualClickerUserDataAccessInterface manualClickerUserDataAccess;

    public MainPresenter(ViewManagerModel viewManagerModel, MainViewModel mainViewModel, ManualClickerUserDataAccessInterface manualClickerUserDataAccess) {
        this.viewManagerModel = viewManagerModel;
        this.mainViewModel = mainViewModel;
        this.manualClickerUserDataAccess = manualClickerUserDataAccess;
    }

    @Override
    public void prepareSuccessView(ManualClickerOutputData outputData) {
        final MainState state = mainViewModel.getState();
        state.setUser(manualClickerUserDataAccess.getUser());
        state.setNewCoinCount(outputData.getNewCoinCount());
        this.mainViewModel.setState(state);
        mainViewModel.firePropertyChange();

        viewManagerModel.setState(mainViewModel.getViewName());
        viewManagerModel.firePropertyChange();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final MainState state = mainViewModel.getState();
        state.setErrorMessage(errorMessage);
        mainViewModel.setState(state);
        mainViewModel.firePropertyChange();

    }
}
