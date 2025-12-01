package game.use_case.PetCard.IncreaseAffection;
import game.Constants;
import game.entity.User;
import game.entity.Pet;
import java.util.*;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionOutputData;

public class IncreaseAffectionInteractor implements IncreaseAffectionInputBoundary {

    private final IncreaseAffectionUserDataAccessInterface dAO;
    private final IncreaseAffectionOutputBoundary userPresenter;

    public IncreaseAffectionInteractor(IncreaseAffectionUserDataAccessInterface daO,
                                       IncreaseAffectionOutputBoundary userPresenter) {
        this.dAO = daO;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(IncreaseAffectionInputData inputData) {

        try {
            String petName = inputData.getPetName();
            User user = dAO.getUser();
            List<Pet> petList = user.getPetInventory();
            for (Pet pet : petList) {
                if (petName.equals(pet.getName())) {
                    pet.increaseAffectionXP(inputData.getAffectionIncrease());
                    if ((pet.getAffectionXP() % 10 == 0) && (pet.getAffectionXP() != Constants.INITIAL_AFFECTION_XP) &&
                            (pet.getAffectionLevel() != Constants.MAX_AFFECTION_LEVEL)) {
                        pet.increaseAffectionLevel();
                        pet.upgradeClickSpeed();
                    }
                    final IncreaseAffectionOutputData outputData = new IncreaseAffectionOutputData(pet.getAffectionXP(), pet.getAffectionLevel());
                    userPresenter.prepareSuccessView(outputData);
                }
            }
        } catch (Exception e) {
            userPresenter.prepareFailView("Pet not found");
        }
    }
}
