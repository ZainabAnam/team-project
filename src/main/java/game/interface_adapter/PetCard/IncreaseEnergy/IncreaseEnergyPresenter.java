package game.interface_adapter.PetCard.IncreaseEnergy;

import game.interface_adapter.ViewManagerModel;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyOutputBoundary;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyOutputData;

public class IncreaseEnergyPresenter implements IncreaseEnergyOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final IncreaseEnergyViewModel increaseEnergyViewModel;

    public IncreaseEnergyPresenter(ViewManagerModel viewManagerModel, IncreaseEnergyViewModel increaseEnergyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.increaseEnergyViewModel = increaseEnergyViewModel;
    }

    @Override
    public void prepareSuccessView(IncreaseEnergyOutputData outputData) {}

    @Override
    public void prepareFailView(String error) {}

}
