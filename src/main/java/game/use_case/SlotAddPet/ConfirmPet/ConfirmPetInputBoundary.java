package game.use_case.SlotAddPet.ConfirmPet;

/*
 * The usecase for confirming a pet and deploying it in the slot.
 * */
public interface ConfirmPetInputBoundary {

    /*
      Execute the Add Pet to Slot Use Case.
      @param SlotAddPetInputData is the input data for this use case.
     */
    void execute(ConfirmPetInputData  confirmPetInputData);
}
