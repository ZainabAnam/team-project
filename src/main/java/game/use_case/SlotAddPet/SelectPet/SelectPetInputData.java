package game.use_case.SlotAddPet.SelectPet;
import game.entity.*;

/*
*  The input data when clicking a slot.
* */
public class SelectPetInputData {
    private final Slot slot;

    public SelectPetInputData(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }
}
