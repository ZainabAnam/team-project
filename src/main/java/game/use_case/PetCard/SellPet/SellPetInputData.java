package game.use_case.PetCard.SellPet;

public class SellPetInputData {
    private final int petIndex; // The index in the Pet Inventory

    public SellPetInputData(int petIndex, String username) {
        this.petIndex = petIndex;
    }

    public SellPetInputData(int petIndex) {
        this.petIndex = petIndex;
    }

    public int getPetIndex() {
        return petIndex;
    }

}
