package game.interface_adapter.GameSave;

import game.use_case.GameSave.SaveOutputBoundary;
import game.use_case.GameSave.SaveOutputData;

/**
 * Presenter for the save game use case
 */
public class SavePresenter implements SaveOutputBoundary {

    private final SaveViewModel saveViewModel;
    public SavePresenter(SaveViewModel saveViewModel) {
        this.saveViewModel = saveViewModel;
    }

    @Override
    public void prepareSuccessView(SaveOutputData data) {
        SaveState state = saveViewModel.getState();

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
