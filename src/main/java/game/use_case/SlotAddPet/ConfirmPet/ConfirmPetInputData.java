package game.use_case.SlotAddPet.ConfirmPet;

/*
 *  The input data for adding a pet to a slot.
 * */
public class ConfirmPetInputData {
    private final int slotID;
    private final String petName;

    public ConfirmPetInputData(int slotID, String pet) {
        this.slotID = slotID;
        this.petName = pet;
    }

    public int getSlot() {
        return slotID;
    }
    public String getPetName() {  return petName;  }
}
