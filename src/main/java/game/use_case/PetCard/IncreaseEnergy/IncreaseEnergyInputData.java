package game.use_case.PetCard.IncreaseEnergy;

import game.entity.Pet;
import game.interface_adapter.collections.CollectionsState;

public class IncreaseEnergyInputData {
    private final String petName;
    private final String food;

    public IncreaseEnergyInputData(String petName, String food) {
        this.petName = petName;
        this.food = food;
    }

    public String getPetName() {
        return petName;
    }

    public String getFood() {
        return food;
    }
}
