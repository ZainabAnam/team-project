package game.use_case.PetCard.SellPet;

public class SellPetInputData {
    private final int petIndex; // The index in the Pet Inventory
    private final String username; // Username

    public SellPetInputData(int petIndex, String username) {
        this.petIndex = petIndex;
        this.username = username;
    }

    public int getPetIndex() {
        return petIndex;
    }

    public String getUsername() {
        return username;
    }
}
