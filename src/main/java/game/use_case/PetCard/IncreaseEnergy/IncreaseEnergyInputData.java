package game.use_case.PetCard.IncreaseEnergy;

import game.entity.Pet;
import game.interface_adapter.collections.CollectionsState;

public class IncreaseEnergyInputData {
    private final CollectionsState.PetCardState pet;
    private final int energyIncrease;

    public IncreaseEnergyInputData(CollectionsState.PetCardState pet, int energyIncrease) {
        this.pet = pet;
        this.energyIncrease = energyIncrease;
    }

    public CollectionsState.PetCardState getPet() {
        return pet;
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
