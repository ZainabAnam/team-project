package game.interface_adapter.collections;

import game.use_case.Collections.CollectionsOutputBoundary;
import game.use_case.Collections.CollectionsOutputData;

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
        // Ensure there is a state object
        CollectionsState state = viewModel.getState();
        if (state == null) {
            state = new CollectionsState();
            viewModel.setState(state);
        }

        // Copies lists from output into state
        state.setPets(outputData.getPets());
        state.setPetFoods(outputData.getPetFoods());
        state.setPetToys(outputData.getPetToys());
        state.setErrorMessage(null);   // clears any previous error

        // Notifies the view that state changed
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
