package game.use_case.SlotAddPet;

import game.entity.*;

/**
 * The output data for the slot clicked use case.
 */
public class SlotAddPetOutputData {

    private final Slot slot;

    public SlotAddPetOutputData(Slot slot) {
        this.slot = slot;
        Pet pet = slot.getPet();
    }
    public Slot getSlot() {
        return slot;
    }

    public String getPetName(){
        return slot.getPet().getName();
    }

    public int getPetEnergyLevel(){
        return slot.getPet().getEnergyLevel();
    }



}
