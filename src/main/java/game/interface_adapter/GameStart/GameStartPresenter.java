package game.interface_adapter.GameStart;

import game.use_case.GameStart.GameStartOutputBoundary;
import game.use_case.GameStart.GameStartOutputData;

/**
 * Presenter for the load use case.
 */
public class GameStartPresenter implements GameStartOutputBoundary {

    private final GameStartViewModel loadViewModel;

    public GameStartPresenter(GameStartViewModel loadViewModel) {
        this.loadViewModel = loadViewModel;
    }

    @Override
    public void prepareSuccessView(GameStartOutputData outputData) {
        GameStartState state = loadViewModel.getState();
        state.setLoadedUser(outputData.getUser());
        state.setLoadSuccessful(true);
        loadViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        GameStartState state = loadViewModel.getState();
        state.setErrorMessage(errorMessage);
        loadViewModel.firePropertyChange();
    }
}
