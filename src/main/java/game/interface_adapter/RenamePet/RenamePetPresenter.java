package game.interface_adapter.RenamePet;

import game.interface_adapter.ViewManagerModel;
import game.use_case.PetCard.RenamePet.RenamePetOutputBoundary;
import game.use_case.PetCard.RenamePet.RenamePetOutputData;

import javax.swing.*;

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

        Timer timer = new Timer(2000, e -> {
            returnToPreviousView();
        });
        timer.setRepeats(false);
        timer.start();
    }
    private void returnToPreviousView() {
        RenamePetState currentState = renamePetViewModel.getState();
        String previousView = currentState.getPreviousView();
        System.out.println("🎯 Returning to shop view"+ previousView);
        // go back to previous page
        if (previousView != null && !previousView.isEmpty()) {
            viewManagerModel.setState(previousView);
        } else {
            viewManagerModel.setState("shop");//return to shop is the default
        }

        viewManagerModel.firePropertyChange();
        renamePetViewModel.setState(new RenamePetState());
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
