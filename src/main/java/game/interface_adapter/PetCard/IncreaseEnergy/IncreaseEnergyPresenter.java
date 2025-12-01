package game.interface_adapter.PetCard.IncreaseEnergy;

import game.interface_adapter.PetCard.PetCardState;
import game.interface_adapter.PetCard.PetCardViewModel;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.collections.CollectionsState;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyOutputBoundary;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyOutputData;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyUserDataAccessInterface;

public class IncreaseEnergyPresenter implements IncreaseEnergyOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PetCardViewModel petCardViewModel;
    private final IncreaseEnergyUserDataAccessInterface increaseEnergyUserDataAccess;

    public IncreaseEnergyPresenter(ViewManagerModel viewManagerModel, PetCardViewModel petCardViewModel, IncreaseEnergyUserDataAccessInterface increaseEnergyUserDataAccess) {
        this.viewManagerModel = viewManagerModel;
        this.petCardViewModel = petCardViewModel;
        this.increaseEnergyUserDataAccess = increaseEnergyUserDataAccess;
    }

    @Override
    public void prepareSuccessView(IncreaseEnergyOutputData outputData) {
        final PetCardState state = petCardViewModel.getState();
        state.setUser(increaseEnergyUserDataAccess.getUser());
        state.setNewEnergyLevel(outputData.getNewEnergyLevel());
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
