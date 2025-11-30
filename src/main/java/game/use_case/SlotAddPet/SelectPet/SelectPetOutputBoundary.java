package game.use_case.SlotAddPet.SelectPet;

/*
* The output boundary for clicking a slot.
*/
public interface SelectPetOutputBoundary {

    /**
    * Prepares the success view for selecting a pet for this use case.
    * @param outputData is the output data
    */
    void preparePetSelectView(SelectPetOutputData outputData);

    /**
     * Prepares the fail view for the Adding Pet to Slot Use Case.
     * Case: Slot is locked
     * @param errorMessage is the error message
     */
    void prepareLockedSlotView(String errorMessage);


}
