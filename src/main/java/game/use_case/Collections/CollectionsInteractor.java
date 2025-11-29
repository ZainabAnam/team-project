package game.use_case.Collections;

import game.entity.User;
import game.entity.Pet;
import game.entity.Item;
import game.entity.PetFood;
import game.entity.PetToy;

import java.util.ArrayList;
import java.util.List;

public class CollectionsInteractor implements CollectionsInputBoundary {

    private final CollectionsDataAccessInterface userDataAccess;
    private final CollectionsOutputBoundary outputBoundary;

    public CollectionsInteractor(CollectionsDataAccessInterface userDataAccess,
                                 CollectionsOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(CollectionsInputData inputData) {
        User user = userDataAccess.getCurrentUser();

        if (user == null) {
            outputBoundary.presentFailure("No current user.");
            return;
        }

        List<Pet> pets = new ArrayList<>(user.getPetInventory());
        List<PetFood> foods = new ArrayList<>();
        List<PetToy> toys = new ArrayList<>();

        for (Item item : user.getItemsList()) {
            if (item instanceof PetFood) {
                foods.add((PetFood) item);
            }
            if (item instanceof PetToy) {
                toys.add((PetToy) item);
            }
        }

        CollectionsOutputData outputData = new CollectionsOutputData(pets, foods, toys);
        outputBoundary.presentSuccess(outputData);
    }
}
