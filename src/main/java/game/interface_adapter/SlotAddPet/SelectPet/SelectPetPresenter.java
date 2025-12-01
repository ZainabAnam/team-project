package game.interface_adapter.SlotAddPet.SelectPet;

import game.entity.User;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.SlotAddPet.ConfirmPet.ConfirmPetViewModel;
import game.use_case.SlotAddPet.SelectPet.SelectPetOutputData;
import game.use_case.SlotAddPet.SelectPet.SelectPetOutputBoundary;

/**
 * The Presenter for the select slot use case.
 */
public class SelectPetPresenter implements SelectPetOutputBoundary {

    private final SelectPetViewModel selectPetViewModel;
    private final ConfirmPetViewModel confirmPetViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectPetPresenter(SelectPetViewModel selectPetViewModel, ConfirmPetViewModel confirmPetViewModel,
                              ViewManagerModel viewManagerModel, User user) {
        this.selectPetViewModel = selectPetViewModel;
        this.confirmPetViewModel = confirmPetViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePetSelectView(SelectPetOutputData response) {
        final SelectPetState selectPetState = new SelectPetState();
        selectPetState.setSlotUnlocked(true);
        selectPetViewModel.firePropertyChange();
    }

    @Override
    public void prepareLockedSlotView(String errorMessage) {
        final SelectPetState selectPetState = selectPetViewModel.getState();
        selectPetState.setMessage(errorMessage);
        selectPetViewModel.firePropertyChange();
    }
}
