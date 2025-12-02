package game.use_case.PetCard.IncreaseAffection;

public class IncreaseAffectionOutputData {
    private final int newAffectionXP;
    private final int newPetLevel;
    private final int newClickSpeed;

    public IncreaseAffectionOutputData(int newAffectionXP, int newPetLevel, int  newClickSpeed) {
        this.newAffectionXP = newAffectionXP;
        this.newPetLevel = newPetLevel;
        this.newClickSpeed = newClickSpeed;
    }

    public int getNewAffectionXP() {
        return newAffectionXP;
    }

    public int getNewPetLevel() {
        return newPetLevel;
    }

    public int getNewClickSpeed() {
        return newClickSpeed;
    }
}
