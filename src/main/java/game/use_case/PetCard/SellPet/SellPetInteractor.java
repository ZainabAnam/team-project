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
        User user = userDataAccess.getCurrentUser();

        if (user == null) {
            outputBoundary.prepareFailView("User not found");
            return;
        }

        // check if index valid
        int petIndex = inputData.getPetIndex();
        if (petIndex < 0 || petIndex >= user.getPetInventory().size()) {
            outputBoundary.prepareFailView("Invalid pet index");
            return;
        }

        // get pet info
        Pet pet = user.getPetInventory().get(petIndex);

        // check if sellable
        if (pet.isDeployed()) {
            outputBoundary.prepareFailView("Cannot sell pet: pet is currently deployed");
            return;
        }

        String petName = pet.getName();
        int sellingPrice = pet.getSellingPrice();

        // sell your pet (sellPetByIndex returns price，>0 when succeeds)
        int result = user.sellPetByIndex(petIndex);

        if (result > 0) {
            // successful sell
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
