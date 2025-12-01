package game.interface_adapter.Sell_Pet;

import game.use_case.PetCard.SellPet.SellPetOutputBoundary;
import game.use_case.PetCard.SellPet.SellPetOutputData;
import game.interface_adapter.ViewManagerModel;

import javax.swing.*;

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

        currentState.setMessage("🎉 Sold " + outputData.getPetName() + " for " +
                outputData.getSellingPrice() + " coins! 💰");

        sellPetViewModel.setState(currentState);
        sellPetViewModel.firePropertyChange();

        Timer timer = new Timer(2000, e -> {
            returnToPreviousView();
        });
        timer.setRepeats(false);
        timer.start();
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
    private void returnToPreviousView() {
        System.out.println("🎯 SellPetPresenter: Returning to collection view");

        // back to collection
        viewManagerModel.setState("collection");
        viewManagerModel.firePropertyChange();

        // clear the state of selling pet
        sellPetViewModel.setState(new SellPetState());
    }
}