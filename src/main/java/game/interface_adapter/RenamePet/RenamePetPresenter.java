package game.interface_adapter.RenamePet;

import game.interface_adapter.ViewManagerModel;
import game.use_case.PetCard.RenamePet.RenamePetOutputBoundary;
import game.use_case.PetCard.RenamePet.RenamePetOutputData;

public class RenamePetPresenter implements RenamePetOutputBoundary {
    private final RenamePetViewModel renamePetViewModel;
    private final ViewManagerModel viewManagerModel;

    public RenamePetPresenter(ViewManagerModel viewManagerModel,
                              RenamePetViewModel renamePetViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.renamePetViewModel = renamePetViewModel;
    }

    @Override
    public void prepareSuccessView(RenamePetOutputData outputData) {
        RenamePetState currentState = renamePetViewModel.getState();
        currentState.setOldName(outputData.getOldName());
        currentState.setNewName(outputData.getNewName());
        currentState.setSuccess(true);
        currentState.setMessage(outputData.getMessage());

        renamePetViewModel.setState(currentState);
        renamePetViewModel.firePropertyChange();

        // 可以在这里触发回到collection view
        // viewManagerModel.setState("collection");
        // viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        RenamePetState currentState = renamePetViewModel.getState();
        currentState.setSuccess(false);
        currentState.setMessage("Rename failed: " + errorMessage);

        renamePetViewModel.setState(currentState);
        renamePetViewModel.firePropertyChange();
    }
}
