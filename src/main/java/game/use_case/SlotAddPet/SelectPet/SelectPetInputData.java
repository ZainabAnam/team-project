package game.use_case.SlotAddPet.SelectPet;
import game.entity.*;

/*
*  The input data when clicking a slot.
* */
public class SelectPetInputData {
    private final Slot slot;
    private final User user;

    public SelectPetInputData(Slot slot,  User user) {
        this.slot = slot;
        this.user = user;
    }

    public Slot getSlot() {
        return slot;
    }
    public User getUser() { return user; }
}
