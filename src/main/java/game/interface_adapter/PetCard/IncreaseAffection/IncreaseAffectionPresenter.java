package game.interface_adapter.PetCard.IncreaseAffection;

import game.interface_adapter.PetCard.PetCardState;
import game.interface_adapter.PetCard.PetCardViewModel;
import game.interface_adapter.ViewManagerModel;
import game.use_case.MainScreenManualClicker.ManualClickerOutputData;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionOutputBoundary;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionOutputData;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionUserDataAccessInterface;

public class IncreaseAffectionPresenter implements IncreaseAffectionOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final PetCardViewModel petCardViewModel;
    private final IncreaseAffectionUserDataAccessInterface increaseAffectionUserDataAccess;

    public IncreaseAffectionPresenter (ViewManagerModel viewManagerModel, PetCardViewModel petCardViewModel, IncreaseAffectionUserDataAccessInterface increaseAffectionUserDataAccess) {
        this.viewManagerModel = viewManagerModel;
        this.petCardViewModel = petCardViewModel;
        this.increaseAffectionUserDataAccess = increaseAffectionUserDataAccess;
    }

    @Override
    public void prepareSuccessView(IncreaseAffectionOutputData outputData) {
        final PetCardState state = petCardViewModel.getState();
        state.setUser(increaseAffectionUserDataAccess.getUser());
        state.setNewAffectionXP(outputData.getNewAffectionXP());
        state.setNewPetLevel(outputData.getNewPetLevel());
        this.petCardViewModel.setState(state);
        petCardViewModel.firePropertyChange();

        viewManagerModel.setState(petCardViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final PetCardState state = petCardViewModel.getState();
        state.setErrorMessage(errorMessage);
        petCardViewModel.setState(state);
        petCardViewModel.firePropertyChange();
    }
}
