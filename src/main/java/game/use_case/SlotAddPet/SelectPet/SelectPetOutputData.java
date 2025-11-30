package game.use_case.SlotAddPet.SelectPet;

import game.entity.*;

import java.util.List;

/**
 * The output data for the slot clicked use case.
 */
public class SelectPetOutputData {

    private final List<Pet> pets;
    private final Slot slot;

    public SelectPetOutputData(Slot slot, List<Pet> pets) {
        this.pets = pets;
        this.slot = slot;
    }
    public Slot getSlot() {
        return slot;
    }
    public List<Pet> getPets() {
        return pets;
    }
}
