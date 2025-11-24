package game.use_case.PetCard.SellPet;

import game.entity.Pet;
import game.entity.User;

public class SellPetInteractor implements SellPetInputBoundary {
    private final SellPetDataAccessInterface userDataAccess;
    private final SellPetOutputBoundary outputBoundary;

    public SellPetInteractor(SellPetDataAccessInterface userDataAccess,
                             SellPetOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SellPetInputData inputData) {
        User user = userDataAccess.getUser(inputData.getUsername());

        if (user == null) {
            outputBoundary.prepareFailView("User not found");
            return;
        }

        // Check if index valid
        if (!user.canSellPet(inputData.getPetIndex())) {
            outputBoundary.prepareFailView("Cannot sell pet: pet may be deployed or invalid index");
            return;
        }

        // Get pet info（before selling）
        Pet pet = user.getPetInventory().get(inputData.getPetIndex());
        String petName = pet.getName();
        int sellingPrice = pet.getSellingPrice();

        // sell your pet
        int result = user.sellPetByIndex(inputData.getPetIndex());

        if (result > 0) {
            // sold
            userDataAccess.saveUser(user);

            SellPetOutputData outputData = new SellPetOutputData(
                    petName,
                    sellingPrice,
                    user.getCoinCount(),
                    true,
                    "Successfully sold " + petName + " for " + sellingPrice + " coins!"
            );
            outputBoundary.prepareSuccessView(outputData);
        } else {
            outputBoundary.prepareFailView("Failed to sell pet");
        }
    }
}
