package game.interface_adapter.SlotAddPet.SelectPet;

import game.entity.Pet;
import game.entity.User;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.SlotAddPet.ConfirmPet.ConfirmPetState;
import game.interface_adapter.SlotAddPet.ConfirmPet.ConfirmPetViewModel;
import game.use_case.SlotAddPet.SelectPet.SelectPetOutputData;
import game.use_case.SlotAddPet.SelectPet.SelectPetOutputBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Presenter for the select slot use case.
 */
public class SelectPetPresenter implements SelectPetOutputBoundary {

    private final SelectPetViewModel selectPetViewModel;
    private final ConfirmPetViewModel confirmPetViewModel;
    private final ViewManagerModel viewManagerModel;
    private final User user;

    public SelectPetPresenter(SelectPetViewModel selectPetViewModel, ConfirmPetViewModel confirmPetViewModel,
                              ViewManagerModel viewManagerModel, User user) {
        this.selectPetViewModel = selectPetViewModel;
        this.confirmPetViewModel = confirmPetViewModel;
        this.viewManagerModel = viewManagerModel;
        this.user = user;
    }

    @Override
    public void preparePetSelectView(SelectPetOutputData response) {
        final ConfirmPetState  confirmPetState = confirmPetViewModel.getState();
        List<Pet> pets = user.getPetInventory();
        List<String> petNames = new ArrayList<>();
        for (Pet p : pets) {
            petNames.add(p.getName());
        }
        this.confirmPetViewModel.firePropertyChange();

        selectPetViewModel.setState(new  SelectPetState());

        this.viewManagerModel.setState(confirmPetViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareLockedSlotView(String errorMessage) {
        final SelectPetState selectPetState = selectPetViewModel.getState();
        selectPetState.setSelectError();
        selectPetViewModel.firePropertyChange();
    }
}
