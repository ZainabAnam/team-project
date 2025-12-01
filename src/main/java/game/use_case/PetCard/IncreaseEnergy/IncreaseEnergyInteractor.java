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
        try {
            String petName = inputData.getPetName();
            User user = dAO.getUser();
            List<Pet> petList = user.getPetInventory();

            for (Pet pet : petList) {
                if (petName.equals(pet.getName()) && user.itemCheck(inputData.getFood())) {
                    user.usePetItem(pet, inputData.getFood());
                    IncreaseEnergyOutputData outputData = new IncreaseEnergyOutputData(pet.getEnergyLevel());
                    userPresenter.prepareSuccessView(outputData);
                }
                else if (!user.itemCheck(inputData.getFood())) {
                    IncreaseEnergyOutputData outputData = new IncreaseEnergyOutputData(pet.getEnergyLevel());
                    userPresenter.prepareFailView("You don't have this food.");
                }
            }
        } catch (Exception e) {
            userPresenter.prepareFailView("Pet not found");
        }
    }
}
