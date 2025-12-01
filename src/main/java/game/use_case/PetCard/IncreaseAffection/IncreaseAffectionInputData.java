package game.use_case.PetCard.IncreaseAffection;

public class IncreaseAffectionInputData {
    private final String petName;
    private final int affectionIncrease;

    public IncreaseAffectionInputData(String petName, int affectionIncrease) {
        this.petName = petName;
        this.affectionIncrease = affectionIncrease;
    }

    public String getPetName() {
        return this.petName;
    }

    public int getAffectionIncrease() {
        return this.affectionIncrease;
    }
}
