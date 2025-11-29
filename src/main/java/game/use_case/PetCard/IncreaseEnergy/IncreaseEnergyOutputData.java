package game.use_case.PetCard.IncreaseEnergy;

public class IncreaseEnergyOutputData {
    private int newEnergyLevel;

    public IncreaseEnergyOutputData(int newEnergyLevel) {
        this.newEnergyLevel = newEnergyLevel;
    }

    public int getNewEnergyLevel() {
        return newEnergyLevel;
    }
}
