package game.interface_adapter.SlotAddPet.SelectPet;

import game.entity.Slot;

/**
 * The state for the Select Slot View model.
 */
public class SelectPetState {

    // Identifies which slot was clicked
    private Slot slot;

    // Whether the slot is unlocked (used by the view)
    private boolean unlocked;

    // Whether the selected slot already has a pet
    private boolean hasPet;

    // Could be the pet name to show in the UI
    private String petName;

    // Error or status message the presenter wants to show
    private String message;

    public

}
