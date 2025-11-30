package game.interface_adapter.PetCard.IncreaseAffection;

import game.interface_adapter.ViewManagerModel;
import game.use_case.MainScreenManualClicker.ManualClickerOutputData;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionOutputBoundary;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionOutputData;

public class IncreaseAffectionPresenter implements IncreaseAffectionOutputBoundary{
    private final ViewManagerModel viewManagerModel;
//  private final IncreaseAffectionViewModel  increaseAffectionViewModel;

    public IncreaseAffectionPresenter (ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(IncreaseAffectionOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
    }
}
