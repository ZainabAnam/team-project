package game.interface_adapter.collections;

import game.use_case.Collections.CollectionsOutputBoundary;
import game.use_case.Collections.CollectionsOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for the Collections use case.
 * Copies data from CollectionsOutputData into CollectionsState
 * and notifies listeners via CollectionsViewModel.
 */
public class CollectionsPresenter implements CollectionsOutputBoundary {

    private final CollectionsViewModel viewModel;

    public CollectionsPresenter(CollectionsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSuccess(CollectionsOutputData outputData) {
        CollectionsState state = new CollectionsState();

        List<CollectionsState.PetCardState> petCards = new ArrayList<>();

        for (CollectionsOutputData.PetInfo pet : outputData.getPets()) {
            CollectionsState.PetCardState card = new CollectionsState.PetCardState();
            card.setName(pet.getName());
            card.setPetVisual(pet.getPetVisual());
            card.setBreed(pet.getBreed());
            card.setLevel(pet.getLevel());
            card.setEnergy(pet.getEnergy());
            card.setAffectionXp(pet.getAffectionXp());
            card.setSellingPrice(pet.getSellingPrice());
            card.setFact(pet.getFact());

            petCards.add(card);
        }

        state.setPets(petCards);

        state.setCannedFoodCount(outputData.getCannedFoodCount());
        state.setKibbleCount(outputData.getKibbleCount());
        state.setHomeCookedCount(outputData.getHomeCookedCount());
        state.setChewToyCount(outputData.getChewToyCount());
        state.setTossToyCount(outputData.getTossToyCount());
        state.setPlushToyCount(outputData.getPlushToyCount());

        state.setErrorMessage(null);

        viewModel.setState(state);
        viewModel.firePropertyChange();
    }


    @Override
    public void presentFailure(String message) {
        CollectionsState state = viewModel.getState();
        if (state == null) {
            state = new CollectionsState();
            viewModel.setState(state);
        }

        // Leaves existing lists as-is; just sets the error message
        state.setErrorMessage(message);

        viewModel.firePropertyChange();
    }
}
