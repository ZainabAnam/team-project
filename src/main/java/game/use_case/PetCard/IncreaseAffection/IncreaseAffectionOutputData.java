package game.use_case.PetCard.IncreaseAffection;

public class IncreaseAffectionOutputData {
    private final int newAffectionXP;
    private final int newPetLevel;

    public IncreaseAffectionOutputData(int newAffectionXP, int newPetLevel) {
        this.newAffectionXP = newAffectionXP;
        this.newPetLevel = newPetLevel;
    }

    public int getNewAffectionXP() {
        return newAffectionXP;
    }

    public int getNewPetLevel() {
        return newPetLevel;
    }
}
