package game.interface_adapter.PetCard.IncreaseEnergy;

import game.Constants;
import game.entity.Pet;
import game.interface_adapter.collections.CollectionsState;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyInputData;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyInputBoundary;

public class IncreaseEnergyController {
    private final IncreaseEnergyInputBoundary interactor;

    public IncreaseEnergyController(IncreaseEnergyInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(CollectionsState.PetCardState pet, String food) {
        String petName = pet.getName();
        int energyIncrease;
        if (food.equals("Kibble")) {
            energyIncrease = Constants.PET_ENERGY_BASIC_INCREASE;
        }
        else if (food.equals("Canned Food")) {
            energyIncrease = Constants.PET_ENERGY_MEDIUM_INCREASE;
        }
        // food is Home-Cooked
        else {
            energyIncrease = Constants.PET_ENERGY_PREMIUM_INCREASE;
        }

        final IncreaseEnergyInputData inputData = new IncreaseEnergyInputData(petName, energyIncrease);
        this.interactor.execute(inputData);
    }
}
