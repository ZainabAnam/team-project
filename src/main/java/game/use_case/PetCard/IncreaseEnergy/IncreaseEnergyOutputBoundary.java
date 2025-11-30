package game.use_case.PetCard.IncreaseEnergy;

import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyOutputData;

public interface IncreaseEnergyOutputBoundary {
    void prepareSuccessView(IncreaseEnergyOutputData outputData);
    void prepareFailView(String error);
}
