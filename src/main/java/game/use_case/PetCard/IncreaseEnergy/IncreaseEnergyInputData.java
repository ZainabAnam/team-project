package game.use_case.PetCard.IncreaseEnergy;

import game.entity.Pet;
import game.interface_adapter.collections.CollectionsState;

public class IncreaseEnergyInputData {
    private final String petName;
    private final int energyIncrease;

    public IncreaseEnergyInputData(String petName, int energyIncrease) {
        this.petName = petName;
        this.energyIncrease = energyIncrease;
    }

    public String getPet() {
        return petName;
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
