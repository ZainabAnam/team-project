package game.use_case.PetCard.IncreaseEnergy;

public class IncreaseEnergyOutputData {
    private int newEnergyLevel;

    public IncreaseEnergyOutputData(int newenergyLevel) {
        this.newEnergyLevel = newenergyLevel;
    }

    public int getNewEnergyLevel() {
        return newEnergyLevel;
    }
}
