package game.use_case.SlotAddPet.ConfirmPet;

import game.entity.Pet;
import game.entity.Slot;

/**
 * The output data for picking a pet to deploy in the slot use case.
 */
public class ConfirmPetOutputData {
    private final Slot slot;
    private final Pet pet;
    public ConfirmPetOutputData(Slot slot, Pet pet) {
        this.slot = slot;
        this.pet = pet;
    }
    public Slot getSlot() {
        return slot;
    }
    public Pet getPet() {
        return pet;
    }
    public String getPetName() {
        return pet.getName();
    }
    public int getPetEnergyLevel() {
        return pet.getEnergyLevel();
    }
}
