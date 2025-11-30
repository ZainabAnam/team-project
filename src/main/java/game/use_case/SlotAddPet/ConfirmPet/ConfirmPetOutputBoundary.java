package game.use_case.SlotAddPet.ConfirmPet;

/*
 * The output boundary for the Adding Pet to Slot use case.
 */
public interface ConfirmPetOutputBoundary {
    /*
     * Prepares the success view after the pet has been selected for the slot.
     * @param outputData is the output data
     */
    void preparePetSlotView(ConfirmPetOutputData outputData);

    /*
     * Prepares the fail view for the Adding Pet to Slot Use Case.
     * Case: Pet is already in another slot.
     * @param outputData is the output data
     */
    void preparePetAlreadyDeployed(String errorMessage);

}
