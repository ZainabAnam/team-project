package game.use_case.SlotAddPet;
import game.entity.*;

/*
*  The input data for adding a pet to a slot.
* */
public class SlotAddPetInputData {
    private Slot slot;
    private Pet pet;

    public SlotAddPetInputData(Slot slot, Pet pet) {
        this.slot = slot;
        this.pet = pet;
    }

    public Slot getSlot() {
        return slot;
    }

    public Pet getPet() {
        return pet;
    }
}
