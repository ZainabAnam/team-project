package game.interface_adapter.SlotAddPet.SelectPet;

import game.entity.Slot;

/**
 * The state for the Select Slot View model.
 */
public class SelectPetState {

    // Identifies which slot was clicked
    private boolean slotUnlocked;
    private String message;

    // Setters
    public void setSlotUnlocked(boolean slotUnlocked) {
        this.slotUnlocked = slotUnlocked;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // Getters
    public boolean isSlotUnlocked() {
        return slotUnlocked;
    }
    public String Message() {
        return message;
    }
}
