package game.use_case.SlotAddPet.SelectPet;

import game.data_access.UserDataAccessObjectInterface;
import game.entity.Pet;
import game.entity.User;

import java.util.List;

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
            List<Pet> temp = (null);

            final SelectPetOutputData slotAddPetOutputData = new SelectPetOutputData(slotAddPetInputData.getSlot(),
                    temp);
            userPresenter.preparePetSelectView(slotAddPetOutputData);
        }
    }
}
