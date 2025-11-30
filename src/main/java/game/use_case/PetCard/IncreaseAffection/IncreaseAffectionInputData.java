package game.use_case.PetCard.IncreaseAffection;

public class IncreaseAffectionInputData {
    private final String userID;
    private final String petName;
    private final int affectionIncrease;

    public IncreaseAffectionInputData(String userID, String petName, int affectionIncrease) {
        this.userID = userID;
        this.petName = petName;
        this.affectionIncrease = affectionIncrease;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPetName() {
        return this.petName;
    }

    public int getAffectionIncrease() {
        return this.affectionIncrease;
    }
}
