package game.use_case.PetCard.IncreaseAffection;

public class IncreaseAffectionInputData {
    private final String petName;
    private final String toy;

    public IncreaseAffectionInputData(String petName, String toy) {
        this.petName = petName;
        this.toy = toy;
    }

    public String getPetName() {
        return this.petName;
    }

    public String getToy() {
        return this.toy;
    }
}
