package game.interface_adapter.Sell_Pet;

import game.use_case.PetCard.SellPet.SellPetInputBoundary;
import game.use_case.PetCard.SellPet.SellPetInputData;

public class SellPetController {
    private final SellPetInputBoundary sellPetUseCaseInteractor;

    public SellPetController(SellPetInputBoundary sellPetUseCaseInteractor) {
        this.sellPetUseCaseInteractor = sellPetUseCaseInteractor;
    }

    public void execute(int petIndex, String username) {
        SellPetInputData inputData = new SellPetInputData(petIndex, username);
        sellPetUseCaseInteractor.execute(inputData);
    }
}
