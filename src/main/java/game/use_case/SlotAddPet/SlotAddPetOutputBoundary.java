package game.use_case.SlotAddPet;

/*
* The output boundary for the Adding Pet to Slot use case.
*/
public interface SlotAddPetOutputBoundary {
    /*
    * Prepares the success view for the Adding Pet to Slot Use Case.
    * @param outputData is the output data
    */
    void prepareSuccessView(SlotAddPetOutputData outputData);

    /*
     * Prepares the fail view for the Adding Pet to Slot Use Case.
     * Case: Slot is locked
     * @param outputData is the output data
     */
    void prepareLockedSlotView(String errorMessage);

    /*
     * Prepares the fail view for the Adding Pet to Slot Use Case.
     * Case: Pet is tired (has 0 energy)
     * @param outputData is the output data
     */
    void preparePetTiredView(String errorMessage);

    /*
     * Prepares the fail view for the Adding Pet to Slot Use Case.
     * Case: Pet is already in another slot.
     * @param outputData is the output data
     */
    void preparePetAlreadyDeployed(String errorMessage);

}
