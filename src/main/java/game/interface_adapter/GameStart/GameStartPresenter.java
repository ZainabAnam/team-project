package game.interface_adapter.GameStart;

import game.use_case.GameStart.GameStartOutputBoundary;
import game.use_case.GameStart.GameStartOutputData;

/**
 * Presenter for the start game use case.
 */
public class GameStartPresenter implements GameStartOutputBoundary {

    private final GameStartViewModel gameStartViewModel;

    public GameStartPresenter(GameStartViewModel gameStartViewModel) {
        this.gameStartViewModel = gameStartViewModel;
    }

    @Override
    public void prepareSuccessView(GameStartOutputData outputData) {
        GameStartState state = gameStartViewModel.getState();
        state.setLoadedUser(outputData.getUser());
        state.setLoadSuccessful(true);
        gameStartViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        GameStartState state = gameStartViewModel.getState();
        state.setErrorMessage(errorMessage);
        gameStartViewModel.firePropertyChange();
    }
}
