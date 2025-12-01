package game.use_case.Collections;

import game.entity.Item;
import game.entity.User;
import game.entity.Pet;
import game.use_case.GetPetFact.GetPetFactOutputData;
import game.use_case.GetPetFact.PetFactDataAccessInterface;
import game.use_case.GetPetFact.PetFactDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.Constants.*;

public class CollectionsInteractor implements CollectionsInputBoundary {

    private final CollectionsOutputBoundary presenter;
    private final CollectionsDataAccessInterface userGateway;
    private final PetFactDataAccessInterface petFactGateway;

    public CollectionsInteractor(CollectionsOutputBoundary presenter,
                                 CollectionsDataAccessInterface userGateway,
                                 PetFactDataAccessInterface petFactGateway) {
        this.presenter = presenter;
        this.userGateway = userGateway;
        this.petFactGateway = petFactGateway;
    }

    @Override
    public void execute(CollectionsInputData inputData) {
        try {
            User user = userGateway.getCurrentUser();

            List<Pet> pets = user.getPetInventory();
            List<CollectionsOutputData.PetInfo> petInfos = new ArrayList<>();

            for (Pet pet : pets) {
                String species = pet.getPetType();
                String breed = pet.getBreed();

                GetPetFactOutputData factOut = petFactGateway.
                        fetchFact(species, breed).
                        orElse(new GetPetFactOutputData("", "", false));

                String factText = factOut.isSuccess() ? factOut.getFactText(): "";

                CollectionsOutputData.PetInfo info =
                        new CollectionsOutputData.PetInfo(
                                pet.getName(),
                                pet.getPetVisual(),
                                pet.getBreed(),
                                pet.getLevel(),
                                pet.getEnergyLevel(),
                                pet.getAffectionXP(),
                                pet.getSellingPrice(),
                                pet.getClickingSpeed(),
                                pet.getPetType(),
                                factText
                        );
                petInfos.add(info);
            }

            HashMap<String, Integer> items = user.getItemsAmountList();

            int canned = items.getOrDefault(ITEM_CANNED_FOOD, 0);
            int kibble = items.getOrDefault(ITEM_KIBBLE, 0);
            int home   = items.getOrDefault(ITEM_HOME_COOKED, 0);

            int chew   = items.getOrDefault(ITEM_CHEW_TOY, 0);
            int toss   = items.getOrDefault(ITEM_TOSS_TOY, 0);
            int plush  = items.getOrDefault(ITEM_PLUSH_TOY, 0);

            CollectionsOutputData output = new CollectionsOutputData(
                    petInfos,
                    canned,
                    kibble,
                    home,
                    chew,
                    toss,
                    plush
            );

            presenter.presentSuccess(output);
        } catch (Exception e) {
            presenter.presentFailure("Failed to load collection.");
        }
    }
}
