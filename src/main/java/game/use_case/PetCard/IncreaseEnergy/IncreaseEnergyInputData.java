package game.use_case.PetCard.IncreaseEnergy;

public class IncreaseEnergyInputData {
    private final String petName;
    private final int energyIncrease;

    public IncreaseEnergyInputData(String petName,  int energyBoost) {
        this.petName = petName;
        this.energyIncrease = energyBoost;
    }

    public String getPetName() {
        return petName;
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
