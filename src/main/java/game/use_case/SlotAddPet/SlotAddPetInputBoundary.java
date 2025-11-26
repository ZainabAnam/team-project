package game.use_case.SlotAddPet;

/*
* The usecase for adding a pet to a slot (to autoclick for the user).
* */
public interface SlotAddPetInputBoundary {

    /*
      Execute the Add Pet to Slot Use Case.
      @param SlotAddPetInputData is the input data for this use case.
     */
    void execute(SlotAddPetInputData slotAddPetInputData);

}
