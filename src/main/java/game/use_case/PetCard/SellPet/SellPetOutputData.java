package game.use_case.PetCard.SellPet;

public class SellPetOutputData {
    private final String petName;
    private final int sellingPrice;
    private final int newCoinCount;
    private final boolean success;
    private final String message;

    public SellPetOutputData(String petName, int sellingPrice, int newCoinCount,
                             boolean success, String message) {
        this.petName = petName;
        this.sellingPrice = sellingPrice;
        this.newCoinCount = newCoinCount;
        this.success = success;
        this.message = message;
    }

    // Getters
    public String getPetName() {
        return petName;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getNewCoinCount() {
        return newCoinCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
