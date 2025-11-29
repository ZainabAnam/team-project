package game.interface_adapter.PetCard.IncreaseAffection;

import game.use_case.MainScreenManualClicker.ManualClickerInputData;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionInputBoundary;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionInputData;

public class IncreaseAffectionController {
    final IncreaseAffectionInputBoundary interactor;

    public IncreaseAffectionController(IncreaseAffectionInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String userID, String petName, int affectionIncrease) {
        final IncreaseAffectionInputData inputData = new IncreaseAffectionInputData(userID, petName, affectionIncrease);
        this.interactor.execute(inputData);
    }
}
