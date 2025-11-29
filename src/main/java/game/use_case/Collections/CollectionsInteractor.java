package game.use_case.Collections;

import game.entity.User;
import game.entity.Pet;
import game.entity.Item;
import game.entity.PetFood;
import game.entity.PetToy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        //TODO: remember, in this output data, u are not outputting the amount of each item
        //u have a hashmap includes all the item name and amount in User class: user.getAmountList()
        for (Map.Entry<String, Item> item : user.getItemsList().entrySet()) {
            if (item.getValue() instanceof PetFood) {
                foods.add((PetFood) item.getValue());
            }
            if (item.getValue() instanceof PetToy) {
                toys.add((PetToy) item.getValue());
            }
        }

        CollectionsOutputData outputData = new CollectionsOutputData(pets, foods, toys);
        outputBoundary.presentSuccess(outputData);
    }
}
