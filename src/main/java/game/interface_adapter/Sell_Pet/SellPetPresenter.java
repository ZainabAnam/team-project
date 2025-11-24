package game.interface_adapter.Sell_Pet;

import game.use_case.PetCard.SellPet.SellPetOutputBoundary;
import game.use_case.PetCard.SellPet.SellPetOutputData;
import game.interface_adapter.ViewManagerModel;

public class SellPetPresenter implements SellPetOutputBoundary {
    private final SellPetViewModel sellPetViewModel;
    private final ViewManagerModel viewManagerModel;

    public SellPetPresenter(ViewManagerModel viewManagerModel,
                            SellPetViewModel sellPetViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.sellPetViewModel = sellPetViewModel;
    }

    @Override
    public void prepareSuccessView(SellPetOutputData outputData) {
        SellPetState currentState = sellPetViewModel.getState();
        currentState.setPetName(outputData.getPetName());
        currentState.setSellingPrice(outputData.getSellingPrice());
        currentState.setNewCoinCount(outputData.getNewCoinCount());
        currentState.setSuccess(true);
        currentState.setMessage(outputData.getMessage());

        sellPetViewModel.setState(currentState);
        sellPetViewModel.firePropertyChange();

        // code jump to collection view
        // viewManagerModel.setState("collection");
        // viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        SellPetState currentState = sellPetViewModel.getState();
        currentState.setSuccess(false);
        currentState.setMessage("Sale failed: " + errorMessage);

        sellPetViewModel.setState(currentState);
        sellPetViewModel.firePropertyChange();
    }
}