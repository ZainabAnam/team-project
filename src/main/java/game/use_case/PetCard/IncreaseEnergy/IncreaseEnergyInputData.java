package game.use_case.PetCard.IncreaseEnergy;

public class IncreaseEnergyInputData {
    private final String userID;
    private final String petName;
    private final int energyIncrease;

    public IncreaseEnergyInputData(String userID, String petName,  int energyBoost) {
        this.userID = userID;
        this.petName = petName;
        this.energyIncrease = energyBoost;
    }

    public String getUserID() {
        return userID;
    }

    public String getPetName() {
        return petName;
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
