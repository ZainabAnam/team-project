package game.use_case.PetCard.SellPet;

public class SellPetInputData {
    private final int petIndex;// The index in the Pet Inventory
    private final String username;

    public SellPetInputData(int petIndex, String username) {
        this.petIndex = petIndex;
        this.username = username;

    }

    public SellPetInputData(int petIndex) {
        this.petIndex = petIndex;
        this.username = null;
    }

    public int getPetIndex() {
        return petIndex;
    }
    public String getUsername() {
        return username;
    }

}
