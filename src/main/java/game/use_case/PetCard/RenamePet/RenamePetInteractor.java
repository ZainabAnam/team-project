package game.use_case.PetCard.RenamePet;

import game.entity.Pet;
import game.entity.User;

public class RenamePetInteractor implements RenamePetInputBoundary {
    private final RenamePetDataAccessInterface userDataAccess;
    private final RenamePetOutputBoundary outputBoundary;

    public RenamePetInteractor(RenamePetDataAccessInterface userDataAccess,
                               RenamePetOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RenamePetInputData inputData) {
        User user = userDataAccess.getUser(inputData.getUsername());

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

        // check if name valid
        String newName = inputData.getNewName().trim();
        if (newName.isEmpty()) {
            outputBoundary.prepareFailView("Pet name cannot be empty");
            return;
        }

        // get pet info and rename
        Pet pet = user.getPetInventory().get(petIndex);
        String oldName = pet.getName();

        // check if name exist(with index select)
        if (isNameDuplicate(user, newName, petIndex)) {
            outputBoundary.prepareFailView("Pet name '" + newName + "' already exists");
            return;
        }

        // rename
        pet.setName(newName);
        userDataAccess.saveUser(user);

        // successful rename
        RenamePetOutputData outputData = new RenamePetOutputData(
                oldName,
                newName,
                true,
                "Successfully renamed " + oldName + " to " + newName + "!"
        );
        outputBoundary.prepareSuccessView(outputData);
    }

    // check if name exist(exclude current pet)
    private boolean isNameDuplicate(User user, String newName, int currentPetIndex) {
        for (int i = 0; i < user.getPetInventory().size(); i++) {
            if (i != currentPetIndex && user.getPetInventory().get(i).getName().equals(newName)) {
                return true;
            }
        }
        return false;
    }
}
