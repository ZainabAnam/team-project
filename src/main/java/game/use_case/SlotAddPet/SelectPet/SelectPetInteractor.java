package game.use_case.SlotAddPet.SelectPet;

import game.data_access.UserDataAccessObjectInterface;
import game.entity.User;

/*
* The Interactor for the Adding Pet to Slot Use Case.
*/
public class SelectPetInteractor implements SelectPetInputBoundary {
    private final SelectPetOutputBoundary userPresenter;
    private final UserDataAccessObjectInterface userData;

    public SelectPetInteractor(SelectPetOutputBoundary userPresenter, UserDataAccessObjectInterface userData) {
        this.userPresenter = userPresenter;
        this.userData = userData;
    }

    @Override
    public void execute(SelectPetInputData slotAddPetInputData) {
        // Slot locked
        if (!slotAddPetInputData.getSlot().isUnlocked()) {
            userPresenter.prepareLockedSlotView("This Slot is locked. Unlock it by purchasing it " +
                    "in the shop.");
        } else {
            User user = UserDataAccessObjectInterface.getUser();

            final SelectPetOutputData slotAddPetOutputData = new SelectPetOutputData(slotAddPetInputData.getSlot(),
                    user.getPetInventory());
            userPresenter.preparePetSelectView(slotAddPetOutputData);
        }
    }
}
