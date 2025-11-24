package game.use_case.SlotAddPet.ConfirmPet;
import game.entity.*;

/*
 *  The input data for adding a pet to a slot.
 * */
public class ConfirmPetInputData {
    private final Slot slot;
    private final Pet pet;

    public ConfirmPetInputData(Slot slot,  Pet pet) {
        this.slot = slot;
        this.pet = pet;
    }

    public Slot getSlot() {
        return slot;
    }
    public Pet getPet() {  return pet;  }
}
