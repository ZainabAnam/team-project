package game.use_case.PetCard.IncreaseAffection;

import game.use_case.MainScreenManualClicker.ManualClickerOutputData;

public interface IncreaseAffectionOutputBoundary {
    void prepareSuccessView(IncreaseAffectionOutputData outputData);
    void prepareFailView(String error);
}
