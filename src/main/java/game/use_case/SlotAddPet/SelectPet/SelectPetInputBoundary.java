package game.use_case.SlotAddPet.SelectPet;

/*
* The usecase for clicking a slot.
* */
public interface SelectPetInputBoundary {

    /*
      Execute the Add Pet to Slot Use Case.
      @param SlotAddPetInputData is the input data for this use case.
     */
    void execute(SelectPetInputData slotAddPetInputData);

}
