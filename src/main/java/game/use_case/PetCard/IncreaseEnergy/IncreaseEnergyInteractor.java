package game.use_case.PetCard.IncreaseEnergy;
import game.entity.User;
import game.entity.Pet;
import game.use_case.MainScreenManualClicker.ManualClickerInputData;
import game.use_case.MainScreenManualClicker.ManualClickerOutputData;
import java.util.List;

public class IncreaseEnergyInteractor implements IncreaseEnergyInputBoundary {
    private final IncreaseEnergyUserDataAccessInterface dAO;
    private final IncreaseEnergyOutputBoundary userPresenter;

    public IncreaseEnergyInteractor(IncreaseEnergyUserDataAccessInterface dAO, IncreaseEnergyOutputBoundary userPresenter) {
        this.dAO = dAO;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute (IncreaseEnergyInputData inputData) {
        if (!dAO.userExists(inputData.getUserID())) {
//            userPresenter.prepareFailView("Energy error.");

        }
        else {
            final User user = dAO.getUser(inputData.getUserID());
            final List<Pet> petInventory = user.getPetInventory();
            final String petName = inputData.getPetName();
            for (Pet pet : petInventory) {
                if (petName.equals(pet.getName())) {
                    pet.increaseEnergyLevel(inputData.getEnergyIncrease());
                    final IncreaseEnergyOutputData outputData = new IncreaseEnergyOutputData(pet.getEnergyLevel());
                    userPresenter.prepareSuccessView(outputData);
                }
            }
            userPresenter.prepareFailView("Energy error");
        }
    }
}
