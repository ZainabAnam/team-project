package game.use_case.SlotAddPet;

import game.entity.*;

/*
* The Interactor for the Adding Pet to Slot Use Case.
*/
public class SlotAddPetInteractor implements SlotAddPetInputBoundary{
    private final SlotAddPetOutputBoundary userPresenter;

    public SlotAddPetInteractor(SlotAddPetOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SlotAddPetInputData slotAddPetInputData) {
        if (!slotAddPetInputData.getSlot().isUnlocked()) {
            userPresenter.prepareLockedSlotView("This Slot is locked. You can buy it in the shop.");
        } else if (slotAddPetInputData.getPet().getEnergyLevel() == 0) {
            userPresenter.preparePetTiredView("This Pet is tired. Deploy anyway?");
        } else if (slotAddPetInputData.getPet().isDeployed()) {
            userPresenter.preparePetAlreadyDeployed("This Pet is already deployed in another slot.");
        }
        else {
            slotAddPetInputData.getSlot().addPet(slotAddPetInputData.getPet());
            slotAddPetInputData.getPet().deployPet();
            final SlotAddPetOutputData slotAddPetOutputData = new SlotAddPetOutputData(slotAddPetInputData.getSlot());
            userPresenter.prepareSuccessView(slotAddPetOutputData);
        }
    }
}
