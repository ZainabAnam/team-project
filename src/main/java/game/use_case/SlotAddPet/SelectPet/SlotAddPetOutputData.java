package game.use_case.SlotAddPet.SelectPet;

import game.entity.*;

/**
 * The output data for the slot clicked use case.
 */
public class SlotAddPetOutputData {

    private final Slot slot;

    public SlotAddPetOutputData(Slot slot) {
        this.slot = slot;
    }
    public Slot getSlot() {
        return slot;
    }
}
