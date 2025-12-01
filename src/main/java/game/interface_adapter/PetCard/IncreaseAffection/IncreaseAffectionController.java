package game.interface_adapter.PetCard.IncreaseAffection;

import game.Constants;
import game.interface_adapter.collections.CollectionsState;
import game.use_case.MainScreenManualClicker.ManualClickerInputData;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionInputBoundary;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionInputData;

public class IncreaseAffectionController {
    final IncreaseAffectionInputBoundary interactor;

    public IncreaseAffectionController(IncreaseAffectionInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(CollectionsState.PetCardState pet, String toy) {
//        String petName = pet.getName();
//        int affectionIncrease;
//        if (toy.equals("Chew Toy")) {
//            affectionIncrease = Constants.PET_TOY_BASIC_AFFECTION_INCREASE;
//        }
//        else if (toy.equals("Toss Toy")) {
//            affectionIncrease = Constants.PET_TOY_MEDIUM_AFFECTION_INCREASE;
//        }
//        // toy is plush toy
//        else {
//            affectionIncrease = Constants.PET_TOY_PREMIUM_AFFECTION_INCREASE;
//        }

        final IncreaseAffectionInputData inputData = new IncreaseAffectionInputData(pet.getName(), toy);
        interactor.execute(inputData);
    }
}
