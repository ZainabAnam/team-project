package game.use_case.SlotAddPet.SelectPet;

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
        // Slot locked
        if (!slotAddPetInputData.getSlot().isUnlocked()) {
            userPresenter.prepareLockedSlotView("This Slot is locked. Unlock it by purchasing it in the shop.");
        } else {
            final SlotAddPetOutputData slotAddPetOutputData = new SlotAddPetOutputData(slotAddPetInputData.getSlot());
            userPresenter.preparePetSelectView(slotAddPetOutputData);
        }
    }
}
