package game.interface_adapter.PetCard.IncreaseEnergy;

import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyInputData;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyInputBoundary;

public class IncreaseEnergyController {
    private final IncreaseEnergyInputBoundary interactor;

    public IncreaseEnergyController(IncreaseEnergyInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final IncreaseEnergyInputData inputData = new IncreaseEnergyInputData("fillerID", "fillerPetName", 1);
        this.interactor.execute(inputData);
    }
}
